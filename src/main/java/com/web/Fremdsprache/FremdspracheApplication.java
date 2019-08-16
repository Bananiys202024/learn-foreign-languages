package com.web.Fremdsprache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class FremdspracheApplication {

	public static void main(String[] args) {
		SpringApplication.run(FremdspracheApplication.class, args);
	}

}
