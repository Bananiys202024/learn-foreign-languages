package com.web.Fremdsprache.initializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.web.Fremdsprache.controllers.AccountController;
import com.web.Fremdsprache.repositories.ConstEnDictionary;

public class Initializator {

	@Autowired
	ConstEnDictionary englishDictionaryRepository;
	
	private static final Logger logger = LogManager.getLogger(Initializator.class);
	
	public static void initalizeMostUsedEnglishWordsToTableMongo() throws IOException {
	
		final File f = new File(Initializator.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	
		logger.info("Path1---"+f);
		
		ArrayList<String> list = new ArrayList<String>();
		
//		Resource[] messageResources = resolver.getResources("classpath*:/static/dictionary/google-10000-english.txt");

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:static/dictionary/google-10000-english.txt");

		for (Resource r: resources) {
		    logger.info("R----"+r);
		}
		    
		
//		Scanner s = new Scanner(new File("classpath*:/static/dictionary/google-10000-english.txt"), "UTF-8"); //source file
//		while (s.hasNext())
//		{
//		    list.add(s.next());
//		}
//		s.close();
//		
//		englishDictionaryRepository.save(entity);
		
	}

}
