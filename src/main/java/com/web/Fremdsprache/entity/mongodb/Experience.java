 package com.web.Fremdsprache.entity.mongodb;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

//@Entity
@Builder
@Document(collection = "experience")
public @Data class Experience {

    @Id
    private Long id;
    
    @Field(value = "exp")
    private Long exp;
    
    @Field(value = "owner")
    private String owner;
    
}
