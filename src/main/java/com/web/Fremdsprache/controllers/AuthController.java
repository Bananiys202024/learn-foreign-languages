package com.web.Fremdsprache.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4203")
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

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBody data) {
        try {
        	logger.info("Email---"+data.getEmail()+"---passwd3e---"+data.getPassword());
            String username = data.getEmail();
            String password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            User found = this.users.findByEmail(username);
            String token = jwtTokenProvider.createToken(username, found.getRoles());
            logger.info("We reached this point");
            
            Map<Object, Object> model = new HashMap<>();
            model.put("email", username); // It is really email, but have name of "username"
            model.put("token", token);
            model.put("password", password);
            model.put("username", found.getUsername());
            model.put("role", found.getRoles().iterator().next().getRole());
            
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(value="/register")
    public ResponseEntity register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
        return new ResponseEntity<String>("User with email: " + user.getEmail() + " already exists", HttpStatus.OK );
        }
        logger.info(user.getUsername()+"----name");
        userService.saveUser(user, "User");
        logger.info("All okey, we registered");
        return new ResponseEntity<String>("User registered successfully", HttpStatus.OK);
    }

    
    @GetMapping(value="/get/logged/name")
    public ResponseEntity<String> register(Principal principal) {
    	try
    	{
        return new ResponseEntity<String>(principal.getName()+"---Logged name", HttpStatus.OK);
    	}
    	catch(NullPointerException e)
    	{
    		logger.error("Error---"+e);
            return new ResponseEntity<String>("Not logged name", HttpStatus.OK);	
    	}
    	catch(Exception e)
    	{
    		logger.error("unfamouse error", e);
            return new ResponseEntity<String>("Some errors", HttpStatus.OK);	
    	}
    	
    }


}