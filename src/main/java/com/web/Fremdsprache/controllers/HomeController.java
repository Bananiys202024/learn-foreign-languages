package com.web.Fremdsprache.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.redis.TrainingWords;
import com.web.Fremdsprache.repositories.TrainingWordRepository;

@RestController
public class HomeController {

	@Autowired
	TrainingWordRepository trainRepository;
	
    @RequestMapping("/")
    public String index() {
        return "hello world from Fremdsprache";
    }
    
    
    @RequestMapping("/redis/write/{word}")
    public String redis_writey(@PathVariable String word) {
    		
    	TrainingWords game = new TrainingWords();
    	game.setEnglishWord(word);
    	game.setAutoCrementId(1L);
    	game.setId("3");
    	game.setOwner("Admin");
    game.setReallyId(4L);
    game.setRussianWord("russianWord");
    
    	    trainRepository.save(game);
  	
        return word;
    }
    
    @RequestMapping("/redis/say")
    public List<TrainingWords> redis_say() {
        return (List<TrainingWords>) trainRepository.findAll();
    }

}
