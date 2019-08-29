package com.web.Fremdsprache.repositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.Fremdsprache.entity.mongodb.ConstEnDict;
import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.model.Size;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.translator.Translator;


public class DictionariesEnglish {

	private static final Logger logger = LogManager.getLogger(DictionariesEnglish.class);

	public static void addWordToEnglishDictionary(DictionaryRepository dictionaryRepository, String word, String owner) throws IOException {
		
		//check if word exists in dictionary
		if(IsWordNotExistsInDictionary(dictionaryRepository, word))
		{
		//try/catch for empty database;
		try
		{
		DictionaryEnglish dictionary = DictionaryEnglish.builder()
				  .owner(owner)
			      .wordEnglish(word)
			      .wordRussian(Translator.translate("ru",word))
			      .id(getMaxId(dictionaryRepository)+1)
			      .repeatTomorrow(false)
			      .dateLearned(new Date())
			      .learned(false)
			      .build();
		
		dictionaryRepository.save(dictionary);
		}
		catch(Exception e)
		{
			DictionaryEnglish dictionary = DictionaryEnglish.builder()
				      .owner(owner)
					  .wordEnglish(word)
				      .wordRussian(Translator.translate("ru",word))
				      .id(1L)
				      .repeatTomorrow(false)
				      .dateLearned(new Date())
				      .learned(false)
				      .build();
			
		dictionaryRepository.save(dictionary);
		}
		}//is word exist in dictionary ?

	}

	private static long getMaxId(DictionaryRepository dictionaryRepository) {
		Optional<DictionaryEnglish> result = dictionaryRepository.findFirstByOrderByIdDesc();
		return result.isPresent()?result.get().getId():0L;
	}

	public static boolean checkIfEnglishDictionaryEmpty(DictionaryRepository dictionaryRepository) {
		return getSizeEnglishDictionary(dictionaryRepository)
									.stream()
									.filter(model -> model.isLearned()==false)
									.filter(model -> model.isRepeatTomorrow()==false)
									.collect(Collectors.toList())
									.size()>0;
	}

	private static List<DictionaryEnglish> getSizeEnglishDictionary(DictionaryRepository dictionaryRepository) {
			return dictionaryRepository.findAll();
	}

	public static  List<DictionaryEnglish> getEnglishDictionary(DictionaryRepository dictionaryRepository) {
		return dictionaryRepository.findAll();
	}

	public static Size getDictionarySize(DictionaryRepository dictionaryRepository) {
		
		List<DictionaryEnglish> list = getSizeEnglishDictionary(dictionaryRepository);
		
		logger.info("List------"+
				 list.stream()
		  .filter(c -> c.isRepeatTomorrow()==false)
		  .filter(c -> c.isLearned() == false)
		  .collect(Collectors.toList())
		  );
		
		Size size = Size.builder()
								.WordsOnLearn(
											  list.stream()
											  .filter(c -> c.isRepeatTomorrow()==false)
											  .filter(c -> c.isLearned() == false)
											  .collect(Collectors.toList()).size()
											)
								.WordsOnRepeat(
												list.stream()
												.filter(c -> c.isRepeatTomorrow()==true)
												.filter(c -> c.isLearned() == false)
												.collect(Collectors.toList()).size()
											  )
								 .Learned(
										list.stream().filter(c -> c.isLearned() == true)
										.collect(Collectors.toList()).size()
									  )
								.build();

		 return size;
		  
	}

	public static void insert_10_random_words(String owner, DictionaryRepository dictionaryRepository,
			ConstEnDictRepo englishDictionaryRepository) throws IOException {
		
		int limit = 1_000; //number of words in dicitonary;

		//generate list of entities
		List<ConstEnDict> entities = new ArrayList<ConstEnDict>();
	
		ConstEnDict entity = null;
		while(entities.size()<10)
		{
		entity = englishDictionaryRepository.findById(new Random().ints(1, 1, limit).findFirst().getAsInt() );
		
		logger.info("---Boollean-----"+entity);
		boolean checkingBool = IsWordNotExistsInDictionary(dictionaryRepository, entity.getWord());
		
		
		
		if(checkingBool)
		{
			entities.add(entity);
		}
		}
		
		//save those 10 entities in dictionaryRepository
		for(ConstEnDict enty:entities)
		dictionaryRepository.save(transformToProperEntity(owner,dictionaryRepository, enty));

	}

	private static DictionaryEnglish transformToProperEntity(String owner, DictionaryRepository dictionaryRepository, ConstEnDict enty) throws IOException {
		
		Optional<DictionaryEnglish> entity = dictionaryRepository.findFirstByOrderByIdDesc();
		
		long maxId = entity.isPresent()?entity.get().getId():0L;	
		
		return DictionaryEnglish.builder()
								.id(maxId+1)
								.dateLearned(new Date())
								.dateRepeat(new Date())
								.learned(false)
								.wordEnglish(enty.getWord())
								.wordRussian(Translator.translate("ru", enty.getWord() ))
								.owner(owner)
								.repeatTomorrow(false)
								.build();
		
	}

	private static boolean IsWordNotExistsInDictionary(DictionaryRepository dictionaryRepository,
			String word) {	
		
		Optional<DictionaryEnglish> result = dictionaryRepository.findBywordEnglish(word) ;	
		
		return !result.isPresent();	
		
	}
	

}
