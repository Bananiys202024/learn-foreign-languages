package com.web.Fremdsprache.entity.mongodb;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "preference")
public @Data class Preference {

    @Id
    private Long id;
    
    @Field(value = "registration_date")
    private Date date_of_registration;
    
    @Field(value = "current_level")
    private int current_level;
    
    @Field(value = "status")
    private String status;

    @Field(value = "enabled")
    private boolean enabled;

    @Field(value = "experience")
    private long experience;
    
    @Field(value = "timezone")
    private String timezone;
    
    @Field(value = "country")
    private String country;

    @Field(value = "native_language")
    private String native_language;
    
    @Field(value = "target_learning_language")
    private String target_learning_language;
    
}
