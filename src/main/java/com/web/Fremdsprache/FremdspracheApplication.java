package com.web.Fremdsprache;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.web.Fremdsprache.entity.mongodb.Role;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.repositories.RoleRepository;
import com.web.Fremdsprache.service.CustomUserDetailsService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class } )
@EnableRedisRepositories
public class FremdspracheApplication {

	public static void main(String[] args) {
		SpringApplication.run(FremdspracheApplication.class, args);
	}
	
}
