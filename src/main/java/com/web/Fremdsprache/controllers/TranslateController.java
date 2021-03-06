package com.web.Fremdsprache.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.translator.Translator;



@CrossOrigin(origins = "http://localhost:4203")
@RestController
public class TranslateController {

	@RequestMapping(value = "/translate/{word}", method = RequestMethod.GET, produces = "application/json")
	public String translateWord(@PathVariable String word) throws IOException {
		return Translator.translate("ru", word);
	}
	
}
