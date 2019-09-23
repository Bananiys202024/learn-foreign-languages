package com.web.Fremdsprache.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.initializer.ActiveInitializingg;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.ConstGmDictRepo;
import com.web.Fremdsprache.repositories.ConstRnDictRepo;

@CrossOrigin(origins = { "http://localhost:4203"})
@RestController
@RequestMapping("/account/settings/")
public class AdminController {

	@Autowired
	ConstEnDictRepo englishDictionaryRepository;
	
	@Autowired
	ConstGmDictRepo  germanDictionaryRepository;
	
	@Autowired
	ConstRnDictRepo  russianDictionaryRepository;
	
	
	//only for admin
	@PutMapping(value = "generate/english/dictionary")
	public void generate_english_dictionary() throws Exception {
		ActiveInitializingg.initalizeMostUsedEnglishWordsToTableMongo(englishDictionaryRepository);
	}
	
	//only for admin
	@PutMapping(value = "generate/russian/dictionary")
	public void generate_russian_dictionary() throws Exception {
		ActiveInitializingg.initalizeMostUsedRussianWordsToTableMongo(russianDictionaryRepository);
	}
	
	//only for admin
	@PutMapping(value = "generate/german/dictionary")
	public void generate_german_dictionary() throws Exception {
		ActiveInitializingg.initalizeMostUsedGermanWordsToTableMongo(germanDictionaryRepository);
	}
}
