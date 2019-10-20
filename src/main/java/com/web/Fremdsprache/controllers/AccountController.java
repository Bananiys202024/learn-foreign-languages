package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.mongodb.Dictionary;
import com.web.Fremdsprache.initializer.InitializingProcess;
import com.web.Fremdsprache.model.BanchProgressData;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.UserRepository;
import com.web.Fremdsprache.repositoryImpl.AccountInformation;
import com.web.Fremdsprache.repositoryImpl.DictionaryImpl;
import com.web.Fremdsprache.repositories.CashExperience;
import com.web.Fremdsprache.repositories.CashePersistence;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.ConstGmDictRepo;
import com.web.Fremdsprache.repositories.ConstRnDictRepo;

@CrossOrigin(origins = { "http://localhost:4203", "https://www.google.com" })
@RestController
@RequestMapping("/account/settings/")
public class AccountController {

	private static final Logger logger = LogManager.getLogger(AccountController.class);


	@Autowired
	public DictionaryRepository dictionaryRepository;
    
	@Autowired
    UserRepository users;
    
    @Autowired
    CashExperience cashExperience;
    
    @Autowired 
    CashePersistence cashe_persistence;
    
	
	//get learned words
    //get detected words
    //get learning words
    //get dictionary words
    //get current level
    //get How do many experience we need for next level
	@GetMapping(value = "banch/data/for/progress/page")
	public BanchProgressData banch_data_progress_page(Principal principal) {
		String loggedUser = principal.getName();
		return AccountInformation.generate_progress_information(dictionaryRepository, users, loggedUser, cashExperience);
	}
	
	@GetMapping(value = "days/persistence/learning")
	public int days_persistence_learning(Principal principal) {
		String loggedUser = principal.getName();
		return AccountInformation.days_persistence_learning(users, loggedUser, cashe_persistence);
	}
	
	
}
