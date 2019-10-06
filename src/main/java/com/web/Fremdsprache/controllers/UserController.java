package com.web.Fremdsprache.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.PreferenceRepository;
import com.web.Fremdsprache.repositories.UserRepository;
import com.web.Fremdsprache.repositoryImpl.AdminProcesses;
import com.web.Fremdsprache.repositoryImpl.UserProcess;

@CrossOrigin(origins = { "http://localhost:4203"})
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserRepository users;
	
    @Autowired
    PreferenceRepository preferenceRepo;
	
	@Autowired
	public DictionaryRepository dictionaryRepository;
	
	//only for admin
	@PutMapping(value = "settings/timezone")
	public ResponseEntity<String> set_timezone_to_user(@RequestBody String timezone, Principal principal) throws Exception {	
		Optional<User> found = users.findByEmail(principal.getName());
		
		if(found.isPresent())
		{
			UserProcess.set_time_zone_user(found, timezone, users, preferenceRepo);
		}
		else
		{
        	return new ResponseEntity<String>("User not found", HttpStatus.OK);
		}

    	return new ResponseEntity<String>("All okey, timezone "+found.get().getEmail()+" is changed", HttpStatus.OK);
	}
		
	//only for admin
	@GetMapping(value = "get/timezone")
	public ResponseEntity<String> get_timezone(Principal principal) throws Exception {	
			Optional<User> found = users.findByEmail(principal.getName());
			
			if(found.isPresent())
			{
				Date date = UserProcess.get_timezone(users, found);
		    	return new ResponseEntity<String>(""+date, HttpStatus.OK);

			}
			else
			{
	        	return new ResponseEntity<String>("User not found", HttpStatus.OK);
			}

	}
	
	//only for admin
	@GetMapping(value = "synchronize/dictionary")
	public ResponseEntity<String> synchronize_dictionary_for_words_on_reapeat(Principal principal) throws Exception {	
			Optional<User> found = users.findByEmail(principal.getName());
			
			if(found.isPresent())
			{
				//logic of refresh redis, updating words on by repeat
				UserProcess.synchronize_dictionary_for_words_on_reapeating_checking_if_day_expired_for_words_on_reapeat(found.get(), dictionaryRepository );
		    	return new ResponseEntity<String>("All okey", HttpStatus.OK);
			}
			else
			{
	        	return new ResponseEntity<String>("Something goes wrong, we can't find your user.", HttpStatus.OK);
			}

	}
	
	
			
	
}
