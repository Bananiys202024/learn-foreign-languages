package com.web.Fremdsprache.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.ConstantEnglishDictionary;
import com.web.Fremdsprache.entity.mongodb.CountExperienceOfTrainingWords;

@Repository
public interface ConstEnDictionary extends MongoRepository<ConstantEnglishDictionary, Integer> {

	ConstantEnglishDictionary findById(int id);
	
}
