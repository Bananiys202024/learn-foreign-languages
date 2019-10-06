package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.mongodb.Dictionary;
import com.web.Fremdsprache.entity.mongodb.Words;
import com.web.Fremdsprache.model.Bool;
import com.web.Fremdsprache.model.Size;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.WordsRepository;
import com.web.Fremdsprache.repositoryImpl.DictionariesEnglish;

@CrossOrigin(origins = "http://localhost:4203")
@RestController
public class DictionaryController {
	
	private static final Logger logger = LogManager.getLogger(DictionaryController.class);
	
	@Autowired
	public DictionaryRepository dictionaryRepository;
	
	@Autowired
	ConstEnDictRepo englishDictionaryRepository;
	
	@Autowired 
	WordsRepository words_repository;
	
	@RequestMapping(value = "/Dictionary/English/empty", method = RequestMethod.GET, produces = "application/json")
	public Bool translateWord(Principal principal) throws IOException {
		String loggedUser = principal.getName();
		boolean result = DictionariesEnglish.checkIfEnglishDictionaryEmpty(loggedUser, words_repository);
		return  Bool.builder()
					.bool(!result) //important !, that logic...yes, i know...but it's work;
					.build();
	}
	
	@RequestMapping(value = "/Dictionary/English/{word}", method = RequestMethod.GET, produces = "application/json")
	public void addNotesToMongoDB(@PathVariable String word, Principal principal) throws IOException {
		String loggedUser = principal.getName();
		DictionariesEnglish.addWordToEnglishDictionary(dictionaryRepository, word, loggedUser);
	}
	
	@RequestMapping(value = "get/Dictionary/size", method = RequestMethod.GET, produces = "application/json")
	public Size dictionarySize(Principal principal) throws IOException {
	String loggedUser = principal.getName();
	return DictionariesEnglish.getDictionarySize(words_repository, loggedUser);
	}
	
	@RequestMapping(value = "get/Dictionary/English", method = RequestMethod.GET, produces = "application/json")
	public List<Words> addNotesToMongoDB(Principal principal) throws IOException {
		String loggedUser = principal.getName();
		return DictionariesEnglish.getSizeEnglishDictionaryByLoggedUser(loggedUser, words_repository);	
	}
	
	@RequestMapping(value = "get/10/random/words/to/dictionary", method = RequestMethod.GET, produces = "application/json")
	public void generate_10_random_words(Principal principal) throws IOException {
		String loggedUser = principal.getName();
		DictionariesEnglish.insert_10_random_words(loggedUser, dictionaryRepository, englishDictionaryRepository);	
	}


	
}
