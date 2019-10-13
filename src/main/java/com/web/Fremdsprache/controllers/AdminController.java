package com.web.Fremdsprache.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.initializer.InitializingProcess;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.ConstGmDictRepo;
import com.web.Fremdsprache.repositories.ConstRnDictRepo;
import com.web.Fremdsprache.repositories.PreferenceRepository;
import com.web.Fremdsprache.repositories.UserRepository;
import com.web.Fremdsprache.repositoryImpl.AdminProcesses;

@CrossOrigin(origins = { "http://localhost:4203"})
@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    UserRepository users;
	
    @Autowired
    PreferenceRepository preferenceRepo;
	
	//only for admin
	@PutMapping(value = "delete/user")
	public ResponseEntity<String> generate_german_dictionary(@RequestBody String email) throws Exception {	
		Optional<User> found = users.findByEmail(email);
		if(found.isPresent())
		{
			AdminProcesses.disable_user_by_email(email, users, found.get(), preferenceRepo);
	    	return new ResponseEntity<String>("All okey, user "+found.get().getEmail()+" is disabled", HttpStatus.OK);
		}
		else
		{
        	return new ResponseEntity<String>("User not found", HttpStatus.OK);
		}
	}
		
}
