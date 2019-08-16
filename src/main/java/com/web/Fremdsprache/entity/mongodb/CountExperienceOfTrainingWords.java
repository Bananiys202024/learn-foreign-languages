package com.web.Fremdsprache.entity.mongodb;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

//@Entity
@Builder
@Document(collection = "countExperienceOfTrainingWords")
public @Data class CountExperienceOfTrainingWords {

    @Id
	public String id; //id as name of owner
    
    @Field(value = "counter")
	public int count; //if reach 3 then goes to 0;
					 //don't forget about logic to add that to experience of dolphi;
	
    
}
