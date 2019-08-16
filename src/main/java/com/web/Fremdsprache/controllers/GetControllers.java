package com.web.Fremdsprache.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "http://localhost:4203")
@RestController
public class GetControllers {

	private static final Logger logger = LogManager.getLogger(GetControllers.class);

//	@RequestMapping(value = "words/onRepeat", method = RequestMethod.GET, produces = "application/json")
//	public void sendWordsOnRepeat() throws IOException {	
//		DictionariesEnglish.sendByRepeat(mistakes, dictionaryRepository);
//	}
		
	
}
