package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.mongodb.ConstEnDict;
import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.countCounterOfExperienceForTrainingWords;

@RestController
public class Testing {

	@Autowired
	ConstEnDictRepo englishDictionaryRepository;
	
	@Autowired
	public DictionaryRepository dictionaryRepository;

	
	@Autowired
	public countCounterOfExperienceForTrainingWords countExperienceOfTrainingWords;
	
	@RequestMapping(value = "/mongo/say/dict", method = RequestMethod.GET, produces = "application/json")
	public List<ConstEnDict> mong_say_dict() throws Exception {
			return englishDictionaryRepository.findAll();
	}
	
	@RequestMapping(value = "/mongo/say/eng", method = RequestMethod.GET, produces = "application/json")
	public List<DictionaryEnglish> mong_say_dffict() throws Exception {
			return dictionaryRepository.findAll();
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = "application/json")
	public String check() throws Exception {
			return "Checking_work_really_work_good_word_last_not_last";
	}
	
//	@RequestMapping(value = "/redis/add", method = RequestMethod.GET, produces = "application/json")
//	public void redis_add() throws Exception {
//	
//		TrainingWords entity = TrainingWords.builder()
//											.autoCrementId(1L)
//											.englishWord("englishWord)")
//											.owner("Admin")
//											.reallyId(5L)
//											.russianWord("Russkoe slovo")						
//											.build();
//		
//		trainingwordRepository.save(entity);
//
//	}
	
//	@RequestMapping(value = "/redis/say", method = RequestMethod.GET, produces = "application/json")
//	public Iterable<TrainingWords> redis_say() throws Exception {
////		return trainingwordRepository.findAll();
//	}
//	
	
	@RequestMapping(value = "mongo/write", method = RequestMethod.GET, produces = "application/json")
	public void sendWordsOnRepeat() throws IOException {	
		DictionaryEnglish entity = new DictionaryEnglish(1L, null, null, null, false, null, false, null);
		dictionaryRepository.save(entity);
	}
	
	@RequestMapping(value = "mongodb/say", method = RequestMethod.GET, produces = "application/json")
	public List<DictionaryEnglish> Mongo_say() throws IOException {	
		return dictionaryRepository.findAll();
	}
	
	
	
}
