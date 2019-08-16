package com.web.Fremdsprache.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.CountExperienceOfTrainingWords;


@Repository
public interface countCounterOfExperienceForTrainingWords extends MongoRepository<CountExperienceOfTrainingWords, String> {

	CountExperienceOfTrainingWords getById(String owner);
	
}
