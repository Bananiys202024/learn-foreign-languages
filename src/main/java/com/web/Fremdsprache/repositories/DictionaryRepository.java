 package com.web.Fremdsprache.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;


@Repository
public interface DictionaryRepository extends MongoRepository<DictionaryEnglish, String> {

	public DictionaryEnglish findFirstByOrderByIdDesc();

	public DictionaryEnglish findBywordEnglish(String word);

}