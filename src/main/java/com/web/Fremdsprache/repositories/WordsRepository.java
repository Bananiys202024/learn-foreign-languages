package com.web.Fremdsprache.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.web.Fremdsprache.entity.mongodb.Dictionary;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.entity.mongodb.Words;

public interface WordsRepository extends MongoRepository<Words, Long>{

	public Optional<Words> findByEnglishWordAndOwner(String word, String owner);

}
