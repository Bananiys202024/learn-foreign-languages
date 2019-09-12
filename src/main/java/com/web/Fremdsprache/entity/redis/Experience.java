package com.web.Fremdsprache.entity.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@RedisHash("experience")
public @Data class Experience {

	@Id
	String user;     //name of loggedUser, user which owner of these data
	int count; 		 //if count equals by 3 then count=0 AND experience+=30;
	long experience; //column for experience;
	
}
