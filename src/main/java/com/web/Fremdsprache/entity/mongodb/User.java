package com.web.Fremdsprache.entity.mongodb;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	    @JsonProperty("preference")
	    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	    private Set<Preference> preference;
	    
	    @DBRef
	    private Set<Role> roles;
}
