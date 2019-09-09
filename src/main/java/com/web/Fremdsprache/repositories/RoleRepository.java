package com.web.Fremdsprache.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.Role;
import com.web.Fremdsprache.entity.mongodb.User;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
	
	Role findByRole(String role);
}