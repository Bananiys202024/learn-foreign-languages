package com.web.Fremdsprache.entity.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "roles")
public class Role {

	    @Id
	    private Long id;
	 
	    @Indexed(unique = true)
	    @Field(value = "email")
	    private String email;
	 
	    @Field(value = "username")
	    private String username;	    
	    
	    @Field(value = "role")
	    private String role;
}
