package com.web.Fremdsprache.entity.mongodb;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "roles")
public @Data class Role {

	    @Id
	    private Long id;
	 
	    @Field(value = "role")
	    private String role;
	    
}
