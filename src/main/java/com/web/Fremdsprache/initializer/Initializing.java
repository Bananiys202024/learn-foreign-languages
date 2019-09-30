package com.web.Fremdsprache.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.web.Fremdsprache.config.JwtTokenProvider;
import com.web.Fremdsprache.entity.mongodb.Preference;
import com.web.Fremdsprache.entity.mongodb.Role;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.repositories.ConstEnDictRepo;
import com.web.Fremdsprache.repositories.ConstGmDictRepo;
import com.web.Fremdsprache.repositories.ConstRnDictRepo;
import com.web.Fremdsprache.repositories.PreferenceRepository;
import com.web.Fremdsprache.repositories.RoleRepository;
import com.web.Fremdsprache.repositories.UserRepository;
import com.web.Fremdsprache.service.CustomUserDetailsService;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class Initializing implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LogManager.getLogger(Initializing.class);
	
	//iniitialize data for admin
	String emailAdmin = "BestJavaDeveloper24@gmail.com"; //admin email;
	String passwordAdmin = "I am Admin"; 			     //admin password;
    
	//initialize data for disabled user
	String disabledUserPassword = "IamDisabledUser";	//disabled user password
	String disabledUserEmail = "IamDisabledUser";		//disabled user email
	
	@Autowired
	ConstEnDictRepo englishDictionaryRepository;
	
	@Autowired
	ConstGmDictRepo  germanDictionaryRepository;
	
	@Autowired
	ConstRnDictRepo  russianDictionaryRepository;
	
	
    @Autowired
    UserRepository users;
    
    @Autowired
    private CustomUserDetailsService userService;
    
    @Autowired 
    RoleRepository roles;
    
	@Autowired
	private  PreferenceRepository preferenceRepository;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Starting passive initializing..");
		
		//checking if Admin role exist in app
		logger.info("Creating admin account...");
		Optional<Role> adminExist = roles.findByRole("Admin");
		List<Preference> preferenceExist = preferenceRepository.findAll();
		logger.info("Checking--" + preferenceExist);
		
		if(adminExist.isPresent())
		{
		logger.info("Admin account already exist");
		}
		else
		{
			User user = new User();
			user.setUsername("Admin");
			user.setEmail(emailAdmin);
			user.setPassword(passwordAdmin.toCharArray());
			
			userService.saveUser(user, "Admin", true);
			logger.info("Admin account created, all okey.");
		}
		//...
		
		//creating disabled user
		logger.info("Creating disabled user account...");
		Optional<User> userDisabledExist = users.findByEmail(disabledUserEmail);
		if(userDisabledExist.isPresent())
		{
			logger.info("disabled user already exist");
		}
		else
		{
			User user = new User();
			user.setUsername("Mortal");
			user.setEmail(disabledUserEmail);
			user.setPassword(disabledUserPassword.toCharArray());
			
			userService.saveUser(user, "User", false);
			logger.info("user-disabled account already created, all okey");
		}
		
		//....
		
		
		logger.info("End passive initializing..");
	
	
		//initializing different dictionaries
		
		//add there is logger initializing dictionaries
		try {
			InitializingProcess.initalizeMostUsedEnglishWordsToTableMongo(englishDictionaryRepository);
			InitializingProcess.initalizeMostUsedRussianWordsToTableMongo(russianDictionaryRepository);
			InitializingProcess.initalizeMostUsedGermanWordsToTableMongo(germanDictionaryRepository);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//add there are success logger initializing dictionaries;

		
	}

}
