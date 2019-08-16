package com.web.Fremdsprache.model;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class Size {

	public int WordsOnLearn;
	public int WordsOnRepeat;
	public int Learned;
	
}
