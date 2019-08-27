package com.web.Fremdsprache.repositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.Fremdsprache.entity.mongodb.ConstEnDict;
import com.web.Fremdsprache.entity.mongodb.CountExperienceOfTrainingWords;
import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.entity.redis.TrainingWords;
import com.web.Fremdsprache.entity.redis.TrainingWordsMistakes;
import com.web.Fremdsprache.model.Mistakes;
import com.web.Fremdsprache.model.Random;
import com.web.Fremdsprache.model.Train;
import com.web.Fremdsprache.repositories.ConstRnDictRepo;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.TrainingWordMistakesRepository;
import com.web.Fremdsprache.repositories.TrainingWordRepository;
import com.web.Fremdsprache.repositories.countCounterOfExperienceForTrainingWords;
import com.web.Fremdsprache.translator.Translator;
import com.web.Fremdsprache.util.Convertor;

public class Training {

	private static final Logger logger = LogManager.getLogger(Training.class);

	public static Train initialize(DictionaryRepository dictionaryRepository) {
		
		List<DictionaryEnglish> list = dictionaryRepository.findAll()
																	.stream()
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
		generated.add(russianRepository.findById(random[j]).getWord());
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
	
//	public static void initializeFiveWordsFromDictionary( DictionaryRepository dictionaryRepository, TrainingWordRepository trainingwordRepository, String owner)
//	{
//	List<DictionaryEnglish> list = getWordOnLearning(dictionaryRepository);
//	logger.info("array from mongodb--"+list);
//	List<TrainingWords> result = Convertor.ConvertFromDictionaryEntityToTrainingWordsEntity(list,owner);
//	logger.info("Initialized array--"+result);
//	result.forEach(entity -> trainingwordRepository.save(entity));
//	}	
//	
//	private static List<DictionaryEnglish> getWordOnLearning(DictionaryRepository dictionaryRepository) {
//		
//		return dictionaryRepository.findAll()
//								   .stream()
//								   .filter(c -> c.isRepeatTomorrow()== false)
//								   .filter(c -> c.isLearned() == false)
//								   .limit(5) //6 excluded, only 5;
//								   .collect(Collectors.toList());
//	}
//
//	public static TrainingWords getEntityById(TrainingWordRepository trainingwordRepository, Long id, String owner) {	
//		Optional<TrainingWords> entity = trainingwordRepository.findById(owner+id);
//		return entity.get();		
//	}
//
//	public static void checkRightAnswerForSecondProcessOfTrainingWords(Long reallyId, String owner, String inputed, String expected, Long id,
//			TrainingWordMistakesRepository trainingwordmistakeRepository) throws IOException {		
//		logger.info("I don't know why, but this line is need for right word of code--"+reallyId); //this line important for right work;
//		if(!expected.toLowerCase().equals(inputed.toLowerCase())) //check match inputed and expected 															 //
//		if(!trainingwordmistakeRepository.findById(owner+""+id).isPresent()) //check if word not exists already in dictionary 
//			trainingwordmistakeRepository.save(generateEntity(reallyId, expected, id, owner));		//save
//
//	
//	}
//
//	private static TrainingWordsMistakes generateEntity(Long reallyId, String expected, Long id, String owner) throws IOException {
//    	return  TrainingWordsMistakes.builder()
//    								.id(owner+""+id)
//    								.reallyId(reallyId)
//    								.englishWord(expected)
//    								.russianWord(Translator.translate("ru", expected))
//    								.owner(owner)
//    								.build();
//	}
//
//	public static void checkRightAnswerForThirdProcessOfTrainingWords(Long reallyId, String owner, String inputed, String expected, Long id,
//			TrainingWordMistakesRepository trainingwordmistakeRepository) throws IOException {
//		logger.info("I don't know why, but this line is need for right word of code"+expected+"---"+inputed+"---"+id); //this line important for right work;
//		if(!expected.toLowerCase().equals(inputed.toLowerCase())) //if wrong go next if //check match inputed and expected //that goes on only if that not right answer																 //
//		if(!trainingwordmistakeRepository.findById(owner+""+id).isPresent()) //check if word not exists already in dictionary //you should watch exclamation mark;
//			trainingwordmistakeRepository.save(generateEntity(reallyId, expected, id, owner)); //save
//
//		
//	}
//
//	public static Mistakes getMistakes(String owner, TrainingWordMistakesRepository trainingwordmistakeRepository) {
//		List<TrainingWordsMistakes> list = getMistakesByDefineUser(trainingwordmistakeRepository, owner);
//		return generateMistakeCount(list);
//	}
//
//	private static List<TrainingWordsMistakes> getMistakesByDefineUser(
//			TrainingWordMistakesRepository trainingwordmistakeRepository, String owner) {
//			List<TrainingWordsMistakes> result = getAllMistakesForUser(owner, trainingwordmistakeRepository);
//		return result.stream().filter(c -> c.getOwner().equals(owner)).collect(Collectors.toList());
//		}
//
//	private static List<TrainingWordsMistakes> getAllMistakesForUser(String owner,
//			TrainingWordMistakesRepository trainingwordmistakeRepository) {
//		
//		List<TrainingWordsMistakes> result = new ArrayList();
//
//		for(int i=1;i<6;i++)
//		{
//		Optional<TrainingWordsMistakes> list = trainingwordmistakeRepository.findById(owner+i);
//		
//		if(list.isPresent()) 
//		{
//		result.add(list.get());
//		}
//		
//		}
//		logger.info("Result-----"+result);
//		return result;
//	}
//
//	private static Mistakes generateMistakeCount(List<TrainingWordsMistakes> list) {
//		return Mistakes.builder()
//				  .right(5 - list.size())
//				  .wrong(list.size())
//				  .build();
//	}
//	
//	public static void sendByRepeat(TrainingWordRepository trainingwordRepository, TrainingWordMistakesRepository trainingwordmistakeRepository, DictionaryRepository dictionaryRepository, String owner) {	
//
//		//get those lists
//		List<TrainingWords> all = (List<TrainingWords>) trainingwordRepository.findAll();	
//		List<TrainingWordsMistakes> mistakes = (List<TrainingWordsMistakes>) trainingwordmistakeRepository.findAll();
//		List<TrainingWords> right = new ArrayList<TrainingWords>();
//		
//		//get list with words without of mistakes
//		for(TrainingWords global:all)
//		for(TrainingWordsMistakes model:mistakes)
//		{
//			if(!global.getEnglishWord().equals(model.getEnglishWord()))
//			{
//				if(!right.contains(global))
//				right.add(global);
//			}
//		}
//		logger.info("Right list---" + right);
//		logger.info("Wrong list---" + mistakes);
//		
//		//convert to words without mistake
//		//convert "right" list to "rightList" list
//		List<DictionaryEnglish> rightList = Convertor.convertFromTrainingWordsToDictionaryEnglishWithRightAnswers(right, owner);
//		
//		logger.info("trace mistake1");
//		
//		//convert to words with mistakes
//		//convert "mistakes" list to "mistakeList" list
//		// i need contain id in "mistake" list, id of even entity;
//		List<DictionaryEnglish> mistakeList = Convertor.convertFromTrainingWordsMistakesToDictionaryEnglishWithWrongAnswers(mistakes, owner);
//		
//		logger.info("trace mistake2");
//		
//		
//		//saving words without mistake
//		for(DictionaryEnglish rightEntity: rightList)
//		dictionaryRepository.save(rightEntity);
//		
//		logger.info("trace mistake3---"+mistakes);
//		
//		
//		//save wirds with mistakes
//		for(DictionaryEnglish mistakeEntity: mistakeList)
//		{
//			logger.info("----Key---"+mistakeEntity);
//			dictionaryRepository.save(mistakeEntity);
//				
//		}
//		
//		logger.info("trace mistake4");
//		
//		
////		//filter by owner
////		right = right.stream().filter(c -> c.getOwner().equals(owner)).collect(Collectors.toList());
////		mistakes = mistakes.stream().filter(c -> c.getOwner().equals(owner)).collect(Collectors.toList());
////		
////		List<DictionaryEnglish> result = new ArrayList<DictionaryEnglish>();
////		
////		logger.info(result+"--Initializing");
////		
////		//get list of entities
////		for(TrainingWordsMistakes model:mistakes)
////		result.add(dictionaryRepository.findBywordEnglish(model.getEnglishWord()));
////							  
////		
////		logger.info(result);
////		//set repeatTommorow
////		for(DictionaryEnglish model:result)
////		{
////			logger.info("---Information---"+model.getWordEnglish());
////		model = DictionaryEnglish.builder()
////								  .wordEnglish(model.getWordEnglish())
////								  .wordRussian(model.getWordRussian())
////								  .repeatTomorrow(true)
////								  .dateRepeat(new Date())
////								  .owner(owner)
////								  .id(model.getId())
////								  .build();
////		}
////		//if right array contains mistakes entitites then delete them
////		//...in process..loading
////		
////		
////		//to save to learnedTables;
////		//...in process..loading
////		
////		
////		//save those entities
////		for(DictionaryEnglish model:result)
////		dictionaryRepository.save(model);
//	}
//
//	public static void cleanSavedData(TrainingWordMistakesRepository trainingwordmistakeRepository,
//			TrainingWordRepository trainingwordRepository, String owner) {
//			for(int i=1;i<6;i++)
//			{
//				trainingwordRepository.deleteById(owner+i);
//				trainingwordmistakeRepository.deleteById(owner+i);
//			}
//			
//			
//	}
//
//	public static int countCounterOfExperience(countCounterOfExperienceForTrainingWords countExperienceOfTrainingWords,
//			String owner) {
//		
//		Optional<CountExperienceOfTrainingWords> model = countExperienceOfTrainingWords.findById(owner);
//		
//		if(model.isPresent())
//		{
//		int count = IncrementExperience(model);	
//			
//		countExperienceOfTrainingWords.save(
//			CountExperienceOfTrainingWords.builder()
//			.id(model.get().getId())
//			.count(count)
//			.build()
//				);
//		return count;
//		}
//		else
//		{
//			countExperienceOfTrainingWords.save(	
//					CountExperienceOfTrainingWords.builder()
//					.id(owner)
//					.count(1)
//					.build()
//					);
//			return 1;
//		}
//	
//		
//		
//	}
//
//	private static int IncrementExperience(Optional<CountExperienceOfTrainingWords> model) {
//		return model.get().getCount() >=3?1:model.get().getCount()+1;
//	}

	
}
