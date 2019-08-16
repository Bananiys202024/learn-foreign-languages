package com.web.Fremdsprache.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.redis.TrainingWordsMistakes;


@Repository
public interface TrainingWordMistakesRepository extends CrudRepository<TrainingWordsMistakes, String> {

	public TrainingWordsMistakes findByEnglishWord(String word);
	
}
