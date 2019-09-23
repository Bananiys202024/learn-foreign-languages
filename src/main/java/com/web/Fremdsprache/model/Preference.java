package com.web.Fremdsprache.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "preference")
public class Preference {

    @Id
    private Long id;
    
    @Field(value = "registration_date")
    private Date date_of_registration;
    
    @Field(value = "status")
    private String status;

    @Field(value = "enabled")
    private boolean enabled;

    @Field(value = "experience")
    private long experience;
    
    @Field(value = "timezone")
    private long timezone;
    
    @Field(value = "country")
    private long country;

    @Field(value = "native_language")
    private long native_language;
    
    @Field(value = "target_learning_language")
    private long target_learning_language;
    
}
