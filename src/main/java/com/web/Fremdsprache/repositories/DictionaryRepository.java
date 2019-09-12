 package com.web.Fremdsprache.repositories;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;


@Repository
public interface DictionaryRepository extends MongoRepository<DictionaryEnglish, String> {

	public Optional<DictionaryEnglish> findFirstByOrderByIdDesc();

	public Optional<DictionaryEnglish> findBywordEnglish(String word);
	
	public Optional<DictionaryEnglish> findBywordEnglishAndOwner(String word, String owner);


}