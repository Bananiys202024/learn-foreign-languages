package com.web.Fremdsprache.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.web.Fremdsprache.config.JwtTokenProvider;
import com.web.Fremdsprache.entity.mongodb.Role;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.repositories.RoleRepository;
import com.web.Fremdsprache.repositories.UserRepository;
import com.web.Fremdsprache.service.CustomUserDetailsService;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class PassiveInitializing implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LogManager.getLogger(PassiveInitializing.class);
	
	//iniitialize data for admin
	String emailAdmin = "BestJavaDeveloper24@gmail.com"; //admin email;
	String passwordAdmin = "I am Admin"; 			     //admin password;
    
    @Autowired
    UserRepository users;
    
    @Autowired
    private CustomUserDetailsService userService;
    
    @Autowired 
    RoleRepository roles;
    
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Starting passive initializing..");
		
		
		//checking if Admin role exist in app
		Optional<Role> adminExist = roles.findByRole("Admin");
		
		if(adminExist.isPresent())
		{
		logger.info("Admin account already exist");
		}
		else
		{
			User user = new User();
			user.setUsername("Admin");
			user.setEmail(emailAdmin);
			user.setPassword(passwordAdmin);
			
			userService.saveUser(user, "Admin");
			logger.info("Admin account created, all okey.");
		}
		//...
		
		
		logger.info("End passive initializing..");
	}

}
