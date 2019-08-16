package com.web.Fremdsprache.entity.redis;

import org.springframework.data.redis.core.RedisHash;



@RedisHash("TrainingWords")
public class TrainingWords {
	   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getAutoCrementId() {
		return autoCrementId;
	}
	public void setAutoCrementId(Long autoCrementId) {
		this.autoCrementId = autoCrementId;
	}
	public Long getReallyId() {
		return reallyId;
	}
	public void setReallyId(Long reallyId) {
		this.reallyId = reallyId;
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
	public TrainingWords() {
		// TODO Auto-generated constructor stub
	}
	private String id;
	private Long   autoCrementId;
	private Long reallyId;
	private String russianWord;
	private String englishWord;
	private String owner;
	
	
}