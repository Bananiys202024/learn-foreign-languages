 package com.web.Fremdsprache.repositories;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.Dictionary;


@Repository
public interface DictionaryRepository extends MongoRepository<Dictionary, String> {

	public Optional<Dictionary> findByOwner(String owner);
	
	public Optional<Dictionary> findFirstByOrderByIdDesc();

	public Optional<Dictionary> findBywordEnglish(String word);
	
	public Optional<Dictionary> findBywordEnglishAndOwner(String word, String owner);


}