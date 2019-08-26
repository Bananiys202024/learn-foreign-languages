package com.web.Fremdsprache.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.ConstRsDict;

@Repository
public interface ConstRnDictRepo extends MongoRepository<ConstRsDict, Integer> {
	
	ConstRsDict findById(int id);
	
}

