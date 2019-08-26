package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.redis.TrainingWords;
import com.web.Fremdsprache.model.Answers;
import com.web.Fremdsprache.model.Mistakes;
import com.web.Fremdsprache.model.Random;
import com.web.Fremdsprache.model.Train;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.TrainingWordMistakesRepository;
import com.web.Fremdsprache.repositories.TrainingWordRepository;
import com.web.Fremdsprache.repositories.countCounterOfExperienceForTrainingWords;
import com.web.Fremdsprache.repositoryImpl.Training;
import com.web.Fremdsprache.util.DictionaryProcess;


@CrossOrigin(origins = "http://localhost:4203")
@RestController
@RequestMapping("/training/")
public class TrainingWordsController {

	private static final Logger logger = LogManager.getLogger(TrainingWordsController.class);
	
	@Autowired
	TrainingWordRepository trainingwordRepository;
	
	@Autowired
	public DictionaryRepository dictionaryRepository;
	
	@Autowired
	public TrainingWordMistakesRepository trainingwordmistakeRepository;
	
	@Autowired
	public countCounterOfExperienceForTrainingWords countExperienceOfTrainingWords;

	String owner ="Admin";
	
//	@RequestMapping(value = "/trainWords/destroyList", method = RequestMethod.GET, produces = "application/json")
//	public void CleanAllSavedData() throws IOException {	
//		TrainingWordsEnglish.cleanSavedData(trainingwordmistakeRepository, trainingwordRepository, owner);
//	}
//	
//	@RequestMapping(value = "/trainWords/initializeList", method = RequestMethod.GET, produces = "application/json")
//	public void initializeLists() throws IOException {	
//		logger.info("initialization");
//		TrainingWordsEnglish.initializeFiveWordsFromDictionary(dictionaryRepository, trainingwordRepository, owner);
//	}
//	
//	@RequestMapping(value = "/get/entity/{id}", method = RequestMethod.GET, produces = "application/json")
//	public TrainingWords getByIdFromInitializedList(@PathVariable Long id) throws IOException {	
//	     return TrainingWordsEnglish.getEntityById(trainingwordRepository, id, owner);
//	}
//	
//	@RequestMapping(value = "/secondProcess/TrainingWord/generate/list/answers/russian/{id}", method = RequestMethod.GET,produces = "application/json")
//	public List<Answers> generateRandomAnswersList(@PathVariable Long id) throws IOException {
//		TrainingWords word = this.getByIdFromInitializedList(id);
//		return DictionaryProcess.generateRussianAnswers(word.getRussianWord());
//	}
//	
//	@RequestMapping(value = "/secondProcess/checkAnswer/{inputed}/{id}/{reallyId}", method = RequestMethod.GET,produces = "application/json")
//	public void checkAnswerForSecondProcess(@PathVariable String inputed, @PathVariable Long id, @PathVariable String reallyId) throws IOException {
//		String expected = this.getByIdFromInitializedList(id).getRussianWord();
//		TrainingWordsEnglish.checkRightAnswerForSecondProcessOfTrainingWords(Long.parseLong(reallyId), owner, inputed, expected, id, trainingwordmistakeRepository);
//	}
//	
//
//	@RequestMapping(value = "/thirdProcess/checkAudioAnswer/{inputed}/{id}/{reallyId}", method = RequestMethod.GET,produces = "application/json")
//	public void getAudioAnswer(@PathVariable String inputed, @PathVariable Long id, @PathVariable String reallyId) throws IOException {	
//		logger.info("Hello from controller---"+id);
//		String expected = this.getByIdFromInitializedList(id).getEnglishWord();
//		TrainingWordsEnglish.checkRightAnswerForThirdProcessOfTrainingWords(Long.parseLong(reallyId), owner, inputed,expected,id,trainingwordmistakeRepository);
//	}
//	
//	
//	@RequestMapping(value = "/trainingWords/getMiistakes", method = RequestMethod.GET,produces = "application/json")
//	public Mistakes mistakes() throws IOException {
//		return TrainingWordsEnglish.getMistakes(owner, trainingwordmistakeRepository);
//	}
//	
//	@RequestMapping(value = "/words/onRepeat/before/tomorrow", method = RequestMethod.GET, produces = "application/json")
//	public void sendWordsOnRepeat() throws IOException {	
//		this.incrementCountExperience();
//		TrainingWordsEnglish.sendByRepeat(trainingwordRepository, trainingwordmistakeRepository, dictionaryRepository, owner);
//	}
//	
//	@RequestMapping(value = "/count/counter/experience/training/words", method = RequestMethod.GET, produces = "application/json")
//	public int incrementCountExperience() throws IOException {	
//		return TrainingWordsEnglish.countCounterOfExperience(countExperienceOfTrainingWords, owner);
//	}
//	
//	@RequestMapping(value = "/empty/controller/only/for/tracing/errors", method = RequestMethod.GET,produces = "application/json")
//	public void trackingeErrors() throws IOException {
//		logger.info(TrainingWordsEnglish.getMistakes(owner, trainingwordmistakeRepository));
//	}

	@GetMapping(value = "initialize", produces = "application/json")
	public Train initialize() throws IOException {
		return Training.initialize(dictionaryRepository);
	}
	
	@GetMapping(value = "generate_random_words/{rightAnswer}", produces = "application/json")
	public Random generate_random_words(@PathVariable String rightAnswer) throws IOException {
		return Training.generateRussianAnswers(rightAnswer);

	}
	
}
