package com.web.Fremdsprache.model;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class TrainWords {

	private int id;
	private String word1;
	private String word2;
	
}
