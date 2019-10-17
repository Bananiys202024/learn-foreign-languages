package com.web.Fremdsprache.repositories;

import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.redis.Experience;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface CashExperience extends CrudRepository<Experience, String>{
	
	Optional<Experience> findById(String loggedUser);

	Optional<List<Experience>> findByUser(String loggedUser);

}
