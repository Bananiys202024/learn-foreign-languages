package com.web.Fremdsprache.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Fremdsprache.entity.mongodb.Preference;
import com.web.Fremdsprache.entity.mongodb.Role;

@Repository
public interface PreferenceRepository extends MongoRepository<Preference, Long> {

}