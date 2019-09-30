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
	    
	    //some explains "Why do i use char[] instead string"
	    //1. if you store the password as plain text 
	    //it will be available in memory 
	    //until the Garbage collector clears it
	    //2. char[] is mutable while String objects are not.
	    //3. Character arrays (char[]) can be cleared after use 
	    //by setting each character to zero and Strings not. 
	    @Field(value = "password")
	    private char[] password;
	    
	    @Transient
	    private String confirmPassword;

	    @DBRef
	    @JsonProperty("preference")
	    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	    private Set<Preference> preference;
	    
	    @DBRef
	    private Set<Role> roles;
}
