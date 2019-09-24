package com.web.Fremdsprache.entity.redis;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import com.web.Fremdsprache.entity.redis.Experience.ExperienceBuilder;

import lombok.Builder;

@Builder
@RedisHash("experience")
public class Persistence {

	@Id
	String owner;
	int days;
	Date date;
	
	@TimeToLive
	  public long getTimeToLive() {
	  	return 86400; //24 hours
	}
}
