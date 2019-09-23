package com.web.Fremdsprache.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.redis.Experience;
import com.web.Fremdsprache.entity.redis.Persistence;

@Repository
public interface CashePersistence extends CrudRepository<Persistence, String>{
	
	Optional<Persistence> findById(String loggedUser);

	Optional<Persistence> findByUser(String loggedUser);

}
