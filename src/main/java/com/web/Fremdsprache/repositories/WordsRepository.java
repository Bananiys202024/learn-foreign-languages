package com.web.Fremdsprache.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.entity.mongodb.Words;

public interface WordsRepository extends MongoRepository<Words, Long>{

}
