package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.model.Bool;
import com.web.Fremdsprache.model.Size;
import com.web.Fremdsprache.repositories.ConstEnDictionary;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositoryImpl.DictionariesEnglish;

@CrossOrigin(origins = "http://localhost:4203")
@RestController
public class DictionaryController {
	
	private static final Logger logger = LogManager.getLogger(DictionaryController.class);
	
	@Autowired
	public DictionaryRepository dictionaryRepository;
	
	@Autowired
	ConstEnDictionary englishDictionaryRepository;
	
	String owner= "Admin";
	
	@RequestMapping(value = "/Dictionary/English/empty", method = RequestMethod.GET, produces = "application/json")
	public Bool translateWord() throws IOException {
		boolean result = DictionariesEnglish.checkIfEnglishDictionaryEmpty(dictionaryRepository);
		return  Bool.builder()
					.bool(!result) //important !, that logic...yes, i know...but it's work;
					.build();
	}
	
	@RequestMapping(value = "/Dictionary/English/{word}", method = RequestMethod.GET, produces = "application/json")
	public void addNotesToMongoDB(@PathVariable String word) throws IOException {
		DictionariesEnglish.addWordToEnglishDictionary(dictionaryRepository, word, owner);
	}
	
	@RequestMapping(value = "get/Dictionary/size", method = RequestMethod.GET, produces = "application/json")
	public Size dictionarySize() throws IOException {
	return DictionariesEnglish.getDictionarySize(dictionaryRepository);
	}
	
	@RequestMapping(value = "get/Dictionary/English", method = RequestMethod.GET, produces = "application/json")
	public List<DictionaryEnglish> addNotesToMongoDB() throws IOException {
		return DictionariesEnglish.getEnglishDictionary(dictionaryRepository);	
	}
	
	@RequestMapping(value = "get/10/random/words/to/dictionary", method = RequestMethod.GET, produces = "application/json")
	public void generate_10_random_words() throws IOException {
		DictionariesEnglish.insert_10_random_words(owner, dictionaryRepository, englishDictionaryRepository);	
	}
	

	
}
