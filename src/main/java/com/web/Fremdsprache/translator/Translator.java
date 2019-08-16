package com.web.Fremdsprache.translator;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.darkprograms.speech.translator.GoogleTranslate;


public class Translator {

	public static String translate(String targetLanguage, String text) throws IOException {

		String myString = GoogleTranslate.translate(targetLanguage, text);
		byte bytes[] = myString.getBytes(); 
		String value = new String(bytes, "UTF-8"); 	
		
		return value;

	}
}
