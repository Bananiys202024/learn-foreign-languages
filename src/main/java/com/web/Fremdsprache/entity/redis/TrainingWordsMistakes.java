package com.web.Fremdsprache.entity.redis;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("TrainingWordsMistakes")
public class TrainingWordsMistakes {
	   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getReallyId() {
		return reallyId;
	}
	public void setReallyId(Long reallyId) {
		this.reallyId = reallyId;
	}
	public Long getAutoCrementId() {
		return autoCrementId;
	}
	public void setAutoCrementId(Long autoCrementId) {
		this.autoCrementId = autoCrementId;
	}
	public String getRussianWord() {
		return russianWord;
	}
	public void setRussianWord(String russianWord) {
		this.russianWord = russianWord;
	}
	public String getEnglishWord() {
		return englishWord;
	}
	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	private String id;
	private Long reallyId;
	private Long autoCrementId;
	private String russianWord;
	private String englishWord;
	private String owner;
	
	
}
