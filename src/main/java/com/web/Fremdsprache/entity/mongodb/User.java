package com.web.Fremdsprache.entity.mongodb;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.web.Fremdsprache.model.Preference;

import lombok.Builder;
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
	    
	    @Field(value = "password")
	    private String password;
	    
	    @Transient
	    private String confirmPassword;

	    @DBRef
	    private Set<Preference> preference;
	    
	    @DBRef
	    private Set<Role> roles;
}
