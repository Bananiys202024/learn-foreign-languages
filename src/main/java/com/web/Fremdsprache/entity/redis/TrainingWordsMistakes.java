package com.web.Fremdsprache.entity.redis;

import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Data;

@RedisHash("TrainingWordsMistakes")
@Builder
public @Data class TrainingWordsMistakes {
	   
	private String id;
	private Long reallyId;
	private Long autoCrementId;
	private String russianWord;
	private String englishWord;
	private String owner;
	
	
}
