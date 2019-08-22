package com.web.Fremdsprache.initializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.web.Fremdsprache.controllers.AccountController;
import com.web.Fremdsprache.entity.mongodb.ConstantEnglishDictionary;
import com.web.Fremdsprache.repositories.ConstEnDictionary;

import java.nio.charset.StandardCharsets;

public class Initializator {

;
	
	private static final Logger logger = LogManager.getLogger(Initializator.class);
	
	public static void initalizeMostUsedEnglishWordsToTableMongo(ConstEnDictionary englishDictionaryRepository) throws Exception {
	
		ArrayList<String> list = new ArrayList<String>();

		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:static/dictionary/google-10000-english.txt");
		
		for (Resource r: resources) {
			InputStream inputStream = r.getInputStream();	  
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			reader.lines().forEach(line -> list.add(line));			
		}

		ArrayList<ConstantEnglishDictionary> entities = generateListEnDictionary(list);
		
		for(ConstantEnglishDictionary entity:entities)
		englishDictionaryRepository.save(entity);
		
	}

	private static ArrayList<ConstantEnglishDictionary> generateListEnDictionary(ArrayList<String> list) {
		
		List<ConstantEnglishDictionary> entities = new ArrayList();

		int count=1;
		
		for(String string:list)
		{
			entities.add(
						ConstantEnglishDictionary.builder()
						.id(count)
						.word(string)
						.build()
						);
		count++;
		}
		
		
		return (ArrayList<ConstantEnglishDictionary>) entities;
	}


}
