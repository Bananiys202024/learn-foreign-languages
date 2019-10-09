package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.model.Answers;
import com.web.Fremdsprache.model.Mistakes;
import com.web.Fremdsprache.model.Random;
import com.web.Fremdsprache.model.Train;
import com.web.Fremdsprache.repositories.CashExperience;
import com.web.Fremdsprache.repositories.ConstRnDictRepo;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.PreferenceRepository;
import com.web.Fremdsprache.repositories.UserRepository;
import com.web.Fremdsprache.repositories.WordsRepository;
import com.web.Fremdsprache.repositories.countCounterOfExperienceForTrainingWords;
import com.web.Fremdsprache.repositoryImpl.Training;
import com.web.Fremdsprache.util.DictionaryProcess;


@CrossOrigin(origins = "http://localhost:4203")
@RestController
@RequestMapping("/training/")
public class TrainingController {

	private static final Logger logger = LogManager.getLogger(TrainingController.class);
	

	@Autowired
	public DictionaryRepository dictionaryRepository;
	
    @Autowired
    UserRepository users;
	
    @Autowired
    CashExperience cashExperience;
    
    @Autowired
    PreferenceRepository preferenceRepo;
    
	@Autowired
	public countCounterOfExperienceForTrainingWords countExperienceOfTrainingWords;

	@Autowired
	public ConstRnDictRepo russianRepository;
	
	@Autowired 
	WordsRepository words_repository;
	
	@GetMapping(value = "initialize", produces = "application/json")
	public Train initialize(Principal principal) throws IOException {
		String loggedUser = principal.getName();
		return Training.initialize(loggedUser, dictionaryRepository);
	}
	
	@GetMapping(value = "generate_random_words/{rightAnswers}", produces = "application/json")
	public Random generate_random_words(@PathVariable String[] rightAnswers) throws IOException {
		return Training.generateRussianAnswers(rightAnswers, russianRepository);
	}

	//get array of right_array(learned_words) and wrong_array(learned_words)
	@PutMapping(value = "conclusion/{right_array}/{wrong_array}")
	public void conclusion_decision_of_destiny_wors(@PathVariable String[] right_array, @PathVariable String[] wrong_array, Principal principal) throws IOException {
		String loggedUser = principal.getName();
		this.conclusion_about_experience(loggedUser);
		Training.conclusion(loggedUser, right_array, wrong_array, dictionaryRepository, words_repository);
	}
	

	private void conclusion_about_experience(String loggedUser) throws IOException {
		Training.conclusion_about_experience(loggedUser, users, cashExperience, preferenceRepo);
	}

}
