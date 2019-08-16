package com.web.Fremdsprache.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.TrainingWordRepository;
import com.web.Fremdsprache.util.Book;


@CrossOrigin(origins = "http://localhost:4203")
@RestController
public class StoryController {

	private static final Logger logger = LogManager.getLogger(StoryController.class);

	@Autowired
	TrainingWordRepository trainingwordRepository;
	
	@Autowired
	public DictionaryRepository dictionaryRepository;

	@RequestMapping(value = "/getStory/{title}", method = RequestMethod.GET,produces = "application/json")
	public ArrayList<String> getBook(@PathVariable String title) throws IOException {
		return Book.getWordsOfBookInListFromDataBase(title);
	}
	
}
