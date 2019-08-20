package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.repositories.DictionaryRepository;



@CrossOrigin(origins = "http://localhost:4203")
@RestController
public class GetControllers {

	private static final Logger logger = LogManager.getLogger(GetControllers.class);

	@Autowired
	public DictionaryRepository dictionaryRepository;

	
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
