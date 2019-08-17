package com.web.Fremdsprache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class } )
@EnableRedisRepositories
public class FremdspracheApplication {

	public static void main(String[] args) {
		SpringApplication.run(FremdspracheApplication.class, args);
	}

}
