package com.web.Fremdsprache.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.Role;
import com.web.Fremdsprache.entity.mongodb.User;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
	
	Optional<Role> findByRole(String role);
}