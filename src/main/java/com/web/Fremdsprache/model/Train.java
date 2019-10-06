package com.web.Fremdsprache.model;

import java.util.ArrayList;
import java.util.List;

import com.web.Fremdsprache.entity.mongodb.Dictionary;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class Train {

	String[] englishWords;
	String[] russianWords;
	
}
