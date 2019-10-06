package com.web.Fremdsprache.entity.mongodb;



import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.persistence.Entity;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dictionaryEnglish")
public @Data class Dictionary {

    @Id
    private Long id;

    @Field(value = "owner")
    private String owner;
    
    private Set<Words> words;
 	
}
