package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.initializer.Initializator;
import com.web.Fremdsprache.model.Bool;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositoryImpl.DictionariesEnglish;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.ConstGmDictRepo;
import com.web.Fremdsprache.repositories.ConstRnDictRepo;

@CrossOrigin(origins = "http://localhost:4203")
@RestController
@RequestMapping("/account/settings/")
public class AccountController {

	private static final Logger logger = LogManager.getLogger(AccountController.class);

	@Autowired
	ConstEnDictRepo englishDictionaryRepository;
	
	@Autowired
	ConstGmDictRepo  germanDictionaryRepository;
	
	@Autowired
	ConstRnDictRepo  russianDictionaryRepository;
	
	@Autowired
	public DictionaryRepository dictionaryRepository;
	
	//only for admin
	@PutMapping(value = "generate/english/dictionary")
	public void generate_english_dictionary() throws Exception {
		Initializator.initalizeMostUsedEnglishWordsToTableMongo(englishDictionaryRepository);
	}
	
	//only for admin
	@PutMapping(value = "generate/russian/dictionary")
	public void generate_russian_dictionary() throws Exception {
		Initializator.initalizeMostUsedRussianWordsToTableMongo(russianDictionaryRepository);
	}
	
	//only for admin
	@PutMapping(value = "generate/german/dictionary")
	public void generate_german_dictionary() throws Exception {
		Initializator.initalizeMostUsedGermanWordsToTableMongo(germanDictionaryRepository);
	}
	
}
