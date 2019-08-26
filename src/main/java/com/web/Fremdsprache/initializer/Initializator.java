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
import com.web.Fremdsprache.entity.mongodb.ConstEnDict;
import com.web.Fremdsprache.entity.mongodb.ConstGmDict;
import com.web.Fremdsprache.entity.mongodb.ConstRsDict;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.ConstGmDictRepo;
import com.web.Fremdsprache.repositories.ConstRnDictRepo;

import java.nio.charset.StandardCharsets;

public class Initializator {

;
	
	private static final Logger logger = LogManager.getLogger(Initializator.class);
	
	public static void initalizeMostUsedEnglishWordsToTableMongo(ConstEnDictRepo englishDictionaryRepository) throws Exception {
	
		ArrayList<String> list = new ArrayList<String>();

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:static/dictionary/english-1000.txt");
		
		for (Resource r: resources) {
			InputStream inputStream = r.getInputStream();	  
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			reader.lines().forEach(line -> list.add(line));			
		}

		ArrayList<ConstEnDict> entities = generateListEnDictionary(list);
		
		for(ConstEnDict entity:entities)
		englishDictionaryRepository.save(entity);
		
	}

	private static ArrayList<ConstEnDict> generateListEnDictionary(ArrayList<String> list) {
		
		List<ConstEnDict> entities = new ArrayList();

		int count=1;
		
		for(String string:list)
		{
			entities.add(
						ConstEnDict.builder()
						.id(count)
						.word(string)
						.build()
						);
		count++;
		}
		
		
		return (ArrayList<ConstEnDict>) entities;
	}

	public static void initalizeMostUsedRussianWordsToTableMongo(ConstRnDictRepo russianDictionaryRepository) throws IOException {

		ArrayList<String> list = new ArrayList<String>();

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:static/dictionary/word_rus.txt");
		
		for (Resource r: resources) {
			InputStream inputStream = r.getInputStream();	  
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			reader.lines().forEach(line -> list.add(line));			
		}

		ArrayList<ConstRsDict> entities = generateListRsDictionary(list);
		
		for(ConstRsDict entity:entities)
		russianDictionaryRepository.save(entity);
	}

	private static ArrayList<ConstRsDict> generateListRsDictionary(ArrayList<String> list) {
		
		List<ConstRsDict> entities = new ArrayList();

		int count=1;
		
		for(String string:list)
		{
			entities.add(
						ConstRsDict.builder()
						.id(count)
						.word(string)
						.build()
						);
		count++;
		}
		
		
		return (ArrayList<ConstRsDict>) entities;
	}

	public static void initalizeMostUsedGermanWordsToTableMongo(ConstGmDictRepo germanDictionaryRepository) throws IOException {
		
		ArrayList<String> list = new ArrayList<String>();

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:static/dictionary/german.txt");
		
		for (Resource r: resources) {
			InputStream inputStream = r.getInputStream();	  
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			reader.lines().forEach(line -> list.add(line));			
		}

		ArrayList<ConstGmDict> entities = generateListGmDictionary(list);
		
		for(ConstGmDict entity:entities)
		germanDictionaryRepository.save(entity);
		
		
	}

	private static ArrayList<ConstGmDict> generateListGmDictionary(ArrayList<String> list) {
		
		List<ConstGmDict> entities = new ArrayList();

		int count=1;
		
		for(String string:list)
		{
			entities.add(
						ConstGmDict.builder()
						.id(count)
						.word(string)
						.build()
						);
		count++;
		}
		
		
		return (ArrayList<ConstGmDict>) entities;
		
	}

	
}
