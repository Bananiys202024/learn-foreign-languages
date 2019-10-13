package com.web.Fremdsprache.controllers;



import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.Fremdsprache.config.JwtTokenProvider;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.model.AuthBody;
import com.web.Fremdsprache.repositories.UserRepository;
import com.web.Fremdsprache.service.CustomUserDetailsService;
import com.web.Fremdsprache.util.ConfirmEmailorPassword;
import com.web.Fremdsprache.util.Creator;
import com.web.Fremdsprache.util.Security;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = { "http://localhost:4203", "https://www.google.com" })
public class AuthController {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @Autowired
    private CustomUserDetailsService userService;

	@Autowired
    private JavaMailSender javaMailSender;
	 
    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBody data) {
       
    	try {

    		String username = data.getEmail();
            char [] password = data.getPassword();
            
            Optional<User> found = this.users.findByEmail(username);
            
            //model for checking  errors
            boolean user_not_found = !found.isPresent();
            boolean user_is_disabled = !found.get().getPreference().iterator().next().isEnabled();

            if(user_not_found)
            return new ResponseEntity<String>("User not found. May be, he does not exist", HttpStatus.OK);
            
            if(user_is_disabled)
            return new ResponseEntity<String>("User is disabled", HttpStatus.OK);
            
            //...end model for checking errors

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, new String(password) ));

            //We are clearing password field for security
            data.setPassword(new char[] {'c', 'l','e','a','r','e','d'});
            
            return Creator.generate_user_response_in_process_login(username, password, found.get(), jwtTokenProvider);
       
    	} catch (AuthenticationException e) {
            return new ResponseEntity<String>("Invalid email/password supplied", HttpStatus.OK);
        }
    	catch(Exception e) {
            return new ResponseEntity<String>("Common error", HttpStatus.OK);
    	}

    }


    @SuppressWarnings("rawtypes")
    @PostMapping(value="/register")
    public ResponseEntity register(@RequestBody User user) {
        Optional<User> userExists = userService.findUserByEmail(user.getEmail());
        
        //checking errors
        if (userExists.isPresent()) 
        return new ResponseEntity<String>("User with email: " + user.getEmail() + " already exists", HttpStatus.OK );
        //...
        
        userService.saveUser(user, "User", true, user.getPreference().iterator().next().getTimezone());
        
        //We are clearing field password for security
        user.setPassword(new char[] {'l','a','t','e'});
        
        return new ResponseEntity<String>("User registered successfully", HttpStatus.OK);
    }

    
    @PostMapping(value="/send/code/by/email")
    public ResponseEntity<String> register(@RequestBody String email) {  	
    	String code = Security.generateRandomSevenElementCode();
    	ConfirmEmailorPassword.sendEmail(code, email, javaMailSender);  	
    	return new ResponseEntity<String>(code, HttpStatus.OK);
    }
    
    @PostMapping(value="/check/exist/email")
    public ResponseEntity<String> check_exist_email(@RequestBody User user) {
        Optional<User> userExists = userService.findUserByEmail(user.getEmail());

        if (userExists.isPresent())
        return new ResponseEntity<String>("User with email: " + user.getEmail() + " already exists", HttpStatus.OK );
        
        return new ResponseEntity<String>("Validation of registration data is ok", HttpStatus.OK);
    }


}