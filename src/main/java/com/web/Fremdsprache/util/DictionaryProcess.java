package com.web.Fremdsprache.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.web.Fremdsprache.model.Answers;


public class DictionaryProcess {
	
	public static List<Answers> generateRussianAnswers(String correctAnswer) throws FileNotFoundException {
		
		Random rand = new Random();
		
		List<Answers> result = new ArrayList<Answers>(4);
		Scanner s = new Scanner(new File("src/main/resources/static/dictionary/word_rus.txt"), "UTF-8");
		
		ArrayList<String> list = new ArrayList<String>();
		
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		

		Collections.shuffle(list);
		
		list = (ArrayList<String>) list.stream()
				.limit(3)
				.collect(Collectors.toList());
		
		for(int i=0;i<3;i++)
		{
			result.add(new Answers(list.get(i)));
		}
		
		result.add(new Answers(correctAnswer));
		
		Collections.shuffle(result);
		
		return result;
	}


}
