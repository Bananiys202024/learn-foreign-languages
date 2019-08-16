package com.web.Fremdsprache.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.redis.TrainingWords;


@Repository
public interface TrainingWordRepository extends CrudRepository<TrainingWords, String> {

	public TrainingWords findByPseudoId(String id);
	
}
