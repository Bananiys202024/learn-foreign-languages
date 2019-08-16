package com.web.Fremdsprache.model;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class Mistakes {

	public int right;
	public int wrong;
}
