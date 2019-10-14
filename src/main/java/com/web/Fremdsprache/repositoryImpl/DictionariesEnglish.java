package com.web.Fremdsprache.repositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.Fremdsprache.entity.mongodb.ConstEnDict;
import com.web.Fremdsprache.entity.mongodb.Dictionary;
import com.web.Fremdsprache.entity.mongodb.Words;
import com.web.Fremdsprache.model.Size;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.WordsRepository;
import com.web.Fremdsprache.translator.Translator;


public class DictionariesEnglish {

	private static final Logger logger = LogManager.getLogger(DictionariesEnglish.class);

	public static void addWordToEnglishDictionary(DictionaryRepository dictionaryRepository, String word, String owner, WordsRepository words_repository) throws IOException {
		
		//check if word exists in dictionary with if condition
		//then execute adding words to english dictionary
		if(IsWordNotExistsInDictionary(dictionaryRepository, word, owner, words_repository)) 
		add_words_to_english_dictionary(dictionaryRepository, word, owner, words_repository);	
		
	}

	private static void add_words_to_english_dictionary(DictionaryRepository dictionaryRepository, String word,
			String owner, WordsRepository words_repository) {
		
		//try/catch for empty database;
		try
		{	
			
			Words words = Words.builder()
					.englishWord(word)
					.russianWord(Translator.translate("ru",word))
					.id(getMaxId(dictionaryRepository)+1)
					.repeatTomorrow(false)
					.dateLearned(new Date())
					.learned(false)	
					.build();	
			
		Dictionary dictionary = Dictionary.builder()
				  .id(getMaxId(dictionaryRepository)+1)
				  .owner(owner)
			      .words(new HashSet<>(Arrays.asList(words)))
			      .build();
		
		dictionaryRepository.save(dictionary);
		}
		catch(Exception e)
		{
			logger.error("Ops!Error!Catched by logger!",e);
		}
		
	

	}

	private static long getMaxId(DictionaryRepository dictionaryRepository) {
		Optional<Dictionary> result = dictionaryRepository.findFirstByOrderByIdDesc();
		return result.isPresent()?result.get().getId():0L;
	}

	public static boolean checkIfEnglishDictionaryEmpty(String loggedUser, WordsRepository words_repository) {
		return getSizeEnglishDictionaryByLoggedUser(loggedUser, words_repository)
									.stream()
									.filter(model -> model.isLearned()==false)
									.filter(model -> model.isRepeatTomorrow()==false)
									.collect(Collectors.toList())
									.size()>0;
	}

	private static List<Dictionary> getSizeEnglishDictionary(DictionaryRepository dictionaryRepository) {
			return dictionaryRepository.findAll();
	}

	public static  List<Dictionary> getEnglishDictionary(DictionaryRepository dictionaryRepository) {
		return dictionaryRepository.findAll();
	}

	public static Size getDictionarySize(WordsRepository words_repository, String loggedUser) {
		
		List<Words> list = getSizeEnglishDictionaryByLoggedUser(loggedUser, words_repository);
		
		final int words_on_learn =  list.stream()
				  								.filter(c -> c.isRepeatTomorrow()==false)
				  								.filter(c -> c.isLearned() == false)
				  								.collect(Collectors.toList()).size();
		final int words_on_repeat = list.stream()
												.filter(c -> c.isRepeatTomorrow()==true)
												.filter(c -> c.isLearned() == false)
												.collect(Collectors.toList()).size();
		final int words_learned = list.stream()
											  .filter(c -> c.isLearned() == true)
											  .collect(Collectors.toList()).size();
		
		Size size = Size.builder()
								.WordsOnLearn(words_on_learn)
								.WordsOnRepeat(words_on_repeat)
								.Learned(words_learned)
								.build();

		 return size;
		  
	}

	public static List<Words> getSizeEnglishDictionaryByLoggedUser(String loggedUser,
			WordsRepository words_repository) {
		return  words_repository.findAll()
									.stream()
									.filter(c -> c.getOwner().equals(loggedUser))
									.collect(Collectors.toList());
	}

	public static void insert_10_random_words(String owner, DictionaryRepository dictionaryRepository,
			ConstEnDictRepo englishDictionaryRepository, WordsRepository words_repository) throws IOException {
		
		int limit = 1_000; //number of words in dicitonary;

		//generate list of entities
		List<ConstEnDict> entities = new ArrayList<ConstEnDict>();
	
		ConstEnDict entity = null;
		
		while(entities.size()<10)
		{
		entity = findEntityConstEnDict(englishDictionaryRepository, limit);
		
		boolean checkingBool = IsWordNotExistsInDictionary(dictionaryRepository, entity.getWord(), owner, words_repository);
		boolean word_not_exist_in_generated_list = checkIfGeneratedListContainWord(entities, entity.getWord());
		
		
		if(checkingBool && word_not_exist_in_generated_list)
		entities.add(entity);
		
		
		}//end while loop
		
		//save those 10 entities in dictionaryRepository
		for(ConstEnDict enty:entities)
		saveToDataBase(words_repository, dictionaryRepository, enty, owner);
	
	}

	private static void saveToDataBase(WordsRepository words_repository, DictionaryRepository dictionaryRepository,
			ConstEnDict enty, String owner) throws IOException {
		

		Optional<Dictionary> entity = dictionaryRepository.findFirstByOrderByIdDesc();
		
		long maxId = entity.isPresent()?entity.get().getId():0L;	
		
		Words words = Words.builder()
				.id(maxId+1)
				.dateLearned(new Date())
				.dateRepeat(new Date())
				.learned(false)
				.englishWord(enty.getWord())
				.russianWord(Translator.translate("ru", enty.getWord() ))
				.owner(owner)
				.repeatTomorrow(false)
				.build();	
		
		Dictionary dictionary =  Dictionary.builder()
			  .id(maxId+1)
			  .owner(owner)
		      .words(new HashSet<>(Arrays.asList(words)))
		      .build();
		
		
		dictionaryRepository.save(dictionary);
		words_repository.save(words);
	}

	private static boolean checkIfGeneratedListContainWord(List<ConstEnDict> entities, String word) {
		
		for(ConstEnDict model:entities)
		if(model.getWord().equals(word))
		return false;
		
		return true;
	}

	private static ConstEnDict findEntityConstEnDict(ConstEnDictRepo englishDictionaryRepository, int limit) {
		
		if(englishDictionaryRepository.findAll().size()==0)
		return ConstEnDict.builder().word("towing").build();	
		
		return englishDictionaryRepository.findById(new Random().ints(1, 1, limit).findFirst().getAsInt() );
	}



	private static boolean IsWordNotExistsInDictionary(DictionaryRepository dictionaryRepository,
			String word, String loggedUser, WordsRepository words_repository) {	
		
		Optional<Words> result = words_repository.findByEnglishWordAndOwner(word, loggedUser) ;	
		
		return !result.isPresent();	
		
	}
	

}
