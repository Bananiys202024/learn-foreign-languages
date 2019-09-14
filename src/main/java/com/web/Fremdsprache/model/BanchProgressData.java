package com.web.Fremdsprache.model;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class BanchProgressData {

	int learned_words;
	int detected_words;
	int all_words_dictionary;
	int learning_words;
	long current_level_experience;
	int experience_before_next_level;
	
	byte experience_earned_today;
	short current_level;
	short before_next_level;
	byte how_do_many_days_persistence_of_learning;
}
