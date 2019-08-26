package com.web.Fremdsprache.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.ConstEnDict;
import com.web.Fremdsprache.entity.mongodb.ConstGmDict;

@Repository
public interface ConstGmDictRepo extends MongoRepository<ConstGmDict, Integer> {
	
	ConstGmDictRepo findById(int id);
	
}

