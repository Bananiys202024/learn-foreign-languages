package com.web.Fremdsprache.repositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.Fremdsprache.entity.mongodb.ConstEnDict;
import com.web.Fremdsprache.entity.mongodb.CountExperienceOfTrainingWords;
import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.entity.redis.Experience;
import com.web.Fremdsprache.model.Mistakes;
import com.web.Fremdsprache.model.Preference;
import com.web.Fremdsprache.model.Random;
import com.web.Fremdsprache.model.Train;
import com.web.Fremdsprache.repositories.CashExperience;
import com.web.Fremdsprache.repositories.ConstRnDictRepo;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.UserRepository;
import com.web.Fremdsprache.repositories.countCounterOfExperienceForTrainingWords;
import com.web.Fremdsprache.translator.Translator;
import com.web.Fremdsprache.util.Convertor;

public class Training {

	private static final Logger logger = LogManager.getLogger(Training.class);

	public static Train initialize(String loggedUser, DictionaryRepository dictionaryRepository) {
		
		List<DictionaryEnglish> list = dictionaryRepository.findAll()
																	.stream()
																	.filter(c -> c.getOwner().equals(loggedUser))
																	.filter(c -> c.isRepeatTomorrow()== false)
																	.filter(c -> c.isLearned() == false)
																	.limit(5) //6 excluded, only 5;
																	.collect(Collectors.toList());
		
		 Train model = Train.builder().englishWords(englishArray(list)).russianWords(russianArray(list)).build();									
						
		 return model;
	}

	private static String[] russianArray(List<DictionaryEnglish> list) {
		
		String[] result = new String[5];
		for(int i=0;i<5;i++)
		result[i]=list.get(i).getWordRussian();
			
		return result;
	}

	private static String[] englishArray(List<DictionaryEnglish> list) {
		
		String[] result = new String[5];
		for(int i=0;i<5;i++)
		result[i]=list.get(i).getWordEnglish();
			
		return result;
	}

	public static Random generateRussianAnswers(String[] rightAnswers, ConstRnDictRepo russianRepository) {
		
		int limit = 34010; // numbers of words in dicitonary;
		
		//generate list of entities
		Random result = new Random();
		ArrayList generated = new ArrayList(4);
		
		//generate random indexex for finding words
		int[] random = new int[3];		
		//...
		
		for(int i=0;i<5;i++)
		{
		generated.clear();
		for(int j=0;j<3;j++)
		{
		//generate random indexex for finding words
		random[j]= new java.util.Random().ints(1, 1, limit).findFirst().getAsInt();
		//...
		generated.add(generateElementOfArray(random[j], russianRepository));
		}
			
		switch(i) 
		{
			case 0: 
					generated.add(rightAnswers[0]);					//add
					Collections.shuffle(generated); 					//shuffle
					result.setGenerate_random_words_1((String[]) generated.toArray(new String[4]));	//add 
					break;											//end
			case 1:
					generated.add(rightAnswers[1]);					//add
					Collections.shuffle(generated); 					//shuffle
					result.setGenerate_random_words_2((String[]) generated.toArray(new String[4]));	//add 
					break;											//end
			case 2:
					generated.add(rightAnswers[2]);					//add
					Collections.shuffle(generated); 					//shuffle
					result.setGenerate_random_words_3((String[]) generated.toArray(new String[4]));	//add 
					break;											//end
			case 3:
					generated.add(rightAnswers[3]);					//add
					Collections.shuffle(generated); 					//shuffle
					result.setGenerate_random_words_4((String[]) generated.toArray(new String[4]));	//add 
					break;											//end
			case 4:
					generated.add(rightAnswers[4]);					//add
					Collections.shuffle(generated); 					//shuffle
					result.setGenerate_random_words_5((String[]) generated.toArray(new String[4]));	//add 
					break;											//end
			default: break;
		}
		
		}	
		
		logger.info("Result----"+result);
		
		return result;
	}

	private static Object generateElementOfArray(int i,ConstRnDictRepo russianRepository) {
		
		if(russianRepository.findAll().size()==0)
		logger.info("You forget initialize russian dictionaries");
		
		return russianRepository.findById(i).getWord();
	}

	public static void conclusion(String loggedUser, String[] right_array, String[] wrong_array,
			DictionaryRepository dictionaryRepository) {
	
		wrong_array = Arrays.stream(wrong_array).filter(e -> !e.equals("null")).toArray(String[]::new);
		right_array = Arrays.stream(right_array).filter(e -> !e.equals("null")).toArray(String[]::new);

		
		List<DictionaryEnglish> right_entity_list = new ArrayList<DictionaryEnglish>();
		List<DictionaryEnglish> wrong_entity_list = new ArrayList<DictionaryEnglish>();

		for(String item:right_array)
		{
			Optional<DictionaryEnglish> found = dictionaryRepository.findBywordEnglishAndOwner(item, loggedUser);
			if(found.isPresent())
			{
			DictionaryEnglish entity = found.get();
			entity.setLearned(true);
			entity.setRepeatTomorrow(false);
			entity.setOwner(loggedUser);
			right_entity_list.add(entity);
			}

		}
		
		for(String item:wrong_array)
		{
			Optional<DictionaryEnglish> found = dictionaryRepository.findBywordEnglishAndOwner(item, loggedUser);
			if(found.isPresent())
			{
			DictionaryEnglish entity = found.get();
			entity.setLearned(false);
			entity.setRepeatTomorrow(true);
			entity.setOwner(loggedUser);
			wrong_entity_list.add(entity);
			}

		}
		
		
		dictionaryRepository.saveAll(right_entity_list);
		dictionaryRepository.saveAll(wrong_entity_list);
	
	}

	public static void conclusion_about_experience(String loggedUser, UserRepository users,
			CashExperience cashExperience) {
		
		//on third time of training you will get +50 exp.
		//time variable can't be more than 3, that's logic of app;
		byte experience = (byte) checkCurrentTimeOfTraining(cashExperience, loggedUser); 

		saveExperienceToUser(loggedUser, users, experience);
//		saveExperienceToCash()
	}

	private static void saveExperienceToUser(String loggedUser, UserRepository users, byte experience) {	
		User found = users.findByEmail(loggedUser);
		Preference preference = found.getPreference().iterator().next();
		preference.setExperience(preference.getExperience()+experience);
		found.setPreference(new HashSet<>(Arrays.asList(preference)));
		users.save(found);
	}

	private static int checkCurrentTimeOfTraining(CashExperience cashExperience, String loggedUser) {
		
		List<Experience> list = (List<Experience>) cashExperience.findAll();
		
		List<Experience> foundList = list.stream().filter(c -> c.getUser().equals(loggedUser)).collect(Collectors.toList());
		logger.info("Onfi-----"+foundList);
		
		
		boolean empty = foundList.size() == 0;
		
		logger.info("Empty-----"+empty);
		
		if(!empty)
		{		
			Experience found = (Experience) list.stream().filter(c -> c.getUser().equals(loggedUser)).collect(Collectors.toList()).get(0);
			logger.info("\n\nfound---"+found+"\n\n");

		byte plusCount = (byte) (found.getCount()+1);
		logger.info("plusCount1-----"+plusCount);
		
		byte experience = (byte) (plusCount>=3?50:20);

		plusCount = plusCount>=3?1:plusCount;
		logger.info("plusCount2-------"+plusCount);
		
		logger.info("experience----"+experience);
		
		byte currentExperience = (byte) found.getExperience();
		logger.info("info-----"+currentExperience);
		
		Experience entity = found;
		entity.setCount(plusCount);
		entity.setExperience(currentExperience+experience);
		cashExperience.save(entity);
		
		return experience;
		}
		else
		{
			Experience entity = Experience.builder().count(1).experience(20).user(loggedUser).build();
			cashExperience.save(entity);
		
		return 20;
		}
		
		
	}

	
}
