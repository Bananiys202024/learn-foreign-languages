package com.web.Fremdsprache.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.ConstEnDict;

@Repository
public interface ConstEnDictRepo extends MongoRepository<ConstEnDict, Integer> {
	
	ConstEnDict findById(int id);
	
}
