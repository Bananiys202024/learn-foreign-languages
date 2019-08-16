package com.web.Fremdsprache.repositoryImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.model.Size;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.translator.Translator;


public class DictionariesEnglish {

	private static final Logger logger = LogManager.getLogger(DictionariesEnglish.class);

	public static void addWordToEnglishDictionary(DictionaryRepository dictionaryRepository, String word, String owner) throws IOException {
		
		//check if word exists in dictionary
		if(IsWordExistsInDictionary(dictionaryRepository, word))
		{
		//try/catch for empty database;
		try
		{
		DictionaryEnglish dictionary = DictionaryEnglish.builder()
				  .owner(owner)
			      .wordEnglish(word)
			      .wordRussian(Translator.translate("ru",word))
			      .id(dictionaryRepository.findFirstByOrderByIdDesc().getId()+1)
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
	
	private static boolean IsWordExistsInDictionary(DictionaryRepository dictionaryRepository,String word) {
		
		DictionaryEnglish result = dictionaryRepository.findBywordEnglish(word);
		
     	if(result==null)
		return true; //all okey, we can save word;
		
				
		return false;
	}

	


	public static boolean checkIfEnglishDictionaryEmpty(DictionaryRepository dictionaryRepository) {
		return getSizeEnglishDictionary(dictionaryRepository).size()>0;
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

}
