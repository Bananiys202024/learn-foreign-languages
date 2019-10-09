package com.web.Fremdsprache.entity.mongodb;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

@Builder
@Document(collection = "words")
public @Data  class Words {

		@Id
	    private Long id;
	    
		@Field(value = "status")
	    private String owner;
		
	    @Field(value = "registration_date")
	    private String russianWord;
	    
	    @Field(value = "current_level")
	    private String englishWord;

	    @Field(value = "reapeatTomorrow")
	    private boolean repeatTomorrow;

	    @Field(value = "dateRepeat")
	    private Date dateRepeat;
	 	
	    @Field(value = "IsItLearned")
	    private boolean learned;
	 	
	    @Field(value = "dateLaerned")
	    private Date dateLearned;
}
