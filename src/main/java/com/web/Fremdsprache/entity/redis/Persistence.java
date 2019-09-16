package com.web.Fremdsprache.entity.redis;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import com.web.Fremdsprache.entity.redis.Experience.ExperienceBuilder;

import lombok.Builder;

@Builder
@RedisHash("experience")
public class Persistence {

	String owner;
	int days;
	
	@TimeToLive
	  public long getTimeToLive() {
	  	return 86400; //24 hours
	}
}
