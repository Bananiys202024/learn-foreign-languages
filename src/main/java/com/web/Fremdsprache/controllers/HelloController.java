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



@CrossOrigin(origins = { "http://localhost:4203", "https://www.google.com" })
@RestController
public class HelloController {

	private static final Logger logger = LogManager.getLogger(HelloController.class);

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
	public String app_say_hello() throws IOException {	
		return "Hello, my name is App and I work good";
	}
			
}
