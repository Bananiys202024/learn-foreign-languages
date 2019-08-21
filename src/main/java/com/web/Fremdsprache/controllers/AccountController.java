package com.web.Fremdsprache.controllers;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.initializer.Initializator;
import com.web.Fremdsprache.model.Bool;
import com.web.Fremdsprache.repositoryImpl.DictionariesEnglish;

@CrossOrigin(origins = "http://localhost:4203")
@RestController
public class AccountController {

	private static final Logger logger = LogManager.getLogger(AccountController.class);

	@RequestMapping(value = "/admin/cabinet/generate/dictionary", method = RequestMethod.GET, produces = "application/json")
	public void translateWord() throws IOException {
		Initializator.initalizeMostUsedEnglishWordsToTableMongo();
	}
	
}
