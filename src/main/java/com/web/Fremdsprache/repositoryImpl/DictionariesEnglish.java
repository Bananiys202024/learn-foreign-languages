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
import java.util.Set;
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
import com.web.Fremdsprache.util.Convertor;
import com.web.Fremdsprache.util.ExtraPackage;


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
		List<Words> found = words_repository.findAll(); 
		
		if(found.size()!=0)
		{
		return  found
				.stream()
				.filter(c -> c.getOwner().equals(loggedUser))
				.collect(Collectors.toList());
		}
		else
		{
			found.add(Words.builder().dateRepeat(new Date()).learned(true).repeatTomorrow(false).build() );
			return found;
		}
		
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
		logger.info("Is this loop ?+"+checkingBool+"--"+word_not_exist_in_generated_list);
		
		}//end while loop
		
		
		//creating list of words for entity dictionaryEnglish;
		Set<Words> list_set_of_words = new HashSet<Words>();
		 
		Optional<Words> found_entity_for_count_max_id = words_repository.findFirstByOrderByIdDesc();
		
		long maxId = found_entity_for_count_max_id.isPresent()?found_entity_for_count_max_id.get().getId():0L;	
		
		//create set collection
		for(ConstEnDict enty:entities)
		{
			list_set_of_words.add(
				Convertor.convert_class_ConstEnDict_to_class_Words_for_process_insert_10_words(enty, maxId, owner)
				);
			
		maxId+=1l;
		}//...end creating list of words;	
		
		//save those 10 entities in dictionaryRepository	
		saveToDataBase(words_repository, dictionaryRepository, list_set_of_words, owner);
	
	}

private static void saveToDataBase(WordsRepository words_repository, DictionaryRepository dictionaryRepository,
			Set<Words> list_set_of_words, String owner) {

		Optional<Dictionary> found_entity_for_count_max_id_for_dictionary = dictionaryRepository.findFirstByOrderByIdDesc();

		long maxId = found_entity_for_count_max_id_for_dictionary.isPresent() ? found_entity_for_count_max_id_for_dictionary.get().getId():0L;	

		//check if dictionary entity with that user already exist; 
		//if already exist then add to current words those words;
		//if not then create new;
				
		Optional<Dictionary> check_by_exist = dictionaryRepository.findByOwner(owner);
		Dictionary dictionary=null;
		
		if(check_by_exist.isPresent())
		{
			dictionary =  Dictionary.builder()
					  .id(check_by_exist.get().getId())
					  .owner(owner)
				      .words(ExtraPackage.mergeSet(list_set_of_words, check_by_exist.get().getWords()))
				      .build();	
		
		}
		else
		{
			dictionary =  Dictionary.builder()
				  .id(maxId+1)
				  .owner(owner)
			      .words(list_set_of_words)
			      .build();
		
		
		}
		//...
		
		dictionaryRepository.save(dictionary);
		words_repository.saveAll(list_set_of_words);
	
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
