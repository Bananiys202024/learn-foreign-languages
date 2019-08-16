package com.web.Fremdsprache.entity.redis;

import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Data;

@RedisHash("TrainingWords")
@Builder
public @Data class TrainingWords {
	   
	private String id;
	private Long   autoCrementId;
	private Long reallyId;
	private String russianWord;
	private String englishWord;
	private String owner;
	
	
}