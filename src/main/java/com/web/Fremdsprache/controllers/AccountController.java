package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.mongodb.ConstantEnglishDictionary;
import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.initializer.Initializator;
import com.web.Fremdsprache.model.Bool;
import com.web.Fremdsprache.repositories.ConstEnDictionary;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositoryImpl.DictionariesEnglish;
import com.web.Fremdsprache.repositories.ConstEnDictionary;

@CrossOrigin(origins = "http://localhost:4203")
@RestController
@RequestMapping("/account/settings/")
public class AccountController {

	private static final Logger logger = LogManager.getLogger(AccountController.class);

	@Autowired
	ConstEnDictionary englishDictionaryRepository;
	
	@Autowired
	public DictionaryRepository dictionaryRepository;
	
	//only for admin
	@RequestMapping(value = "generate/english/dictionary", method = RequestMethod.GET, produces = "application/json")
	public void translateWord() throws Exception {
		Initializator.initalizeMostUsedEnglishWordsToTableMongo(englishDictionaryRepository);
	}
	
	
	@RequestMapping(value = "mongo/say/dict", method = RequestMethod.GET, produces = "application/json")
	public List<ConstantEnglishDictionary> mong_say_dict() throws Exception {
			return englishDictionaryRepository.findAll();
	}
	
	@RequestMapping(value = "mongo/say/eng", method = RequestMethod.GET, produces = "application/json")
	public List<DictionaryEnglish> mong_say_dffict() throws Exception {
			return dictionaryRepository.findAll();
	}
	
	
}
