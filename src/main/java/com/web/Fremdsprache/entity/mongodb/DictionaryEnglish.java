package com.web.Fremdsprache.entity.mongodb;



import java.util.Date;

import javax.persistence.Entity;

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
public @Data class DictionaryEnglish {

    @Id
    private Long id;

    @Field(value = "owner")
    private String owner;
    
    @Field(value = "wordEnglish")
    private String wordEnglish;
 
    @Field(value = "wordRussian")
    private String wordRussian;
    
    @Field(value = "reapeatTomorrow")
    private boolean repeatTomorrow;

    @Field(value = "dateRepeat")
    private Date dateRepeat;
 	
    @Field(value = "IsItLearned")
    private boolean learned;
 	
    @Field(value = "dateLaerned")
    private Date dateLearned;
 	
}
