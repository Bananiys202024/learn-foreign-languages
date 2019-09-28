package com.web.Fremdsprache.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.ConstEnDict;
import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.entity.mongodb.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findFirstByOrderByIdDesc();

}