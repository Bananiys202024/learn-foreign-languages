package com.web.Fremdsprache.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Book {

	private static final Logger logger = LogManager.getLogger(Book.class);

	
	public static ArrayList<String> getWordsOfBookInListFromDataBase(String title) throws FileNotFoundException
	{
		
//		Path path = FileSystems.getDefault().getPath(Book.class);
		logger.info("Path--updated------"+ Book.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		
		Scanner s = new Scanner(new File("src/main/resources/static/books/Atlas-Broken.txt"), "UTF-8");
		
		ArrayList<String> list = new ArrayList<String>();
		
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
			
		
		return (ArrayList<String>) list.stream().limit(150).collect(Collectors.toList());	
	}
}
