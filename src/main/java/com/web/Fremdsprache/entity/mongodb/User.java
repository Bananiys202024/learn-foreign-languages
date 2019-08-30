package com.web.Fremdsprache.entity.mongodb;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "users")
public @Data class User {

	    @Id
	    private Long id;
	 
	    @Indexed(unique = true)
	    @Field(value = "email")
	    private String email;
	 
	    @Field(value = "username")
	    private String username;
	 
	    @Field(value = "registration_date")
	    private Date date_of_registration;
	    
	    private long experience;
}
