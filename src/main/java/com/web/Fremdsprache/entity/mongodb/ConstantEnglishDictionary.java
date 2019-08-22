package com.web.Fremdsprache.entity.mongodb;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.web.Fremdsprache.entity.mongodb.CountExperienceOfTrainingWords.CountExperienceOfTrainingWordsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "constantDictionary")
public @Data class ConstantEnglishDictionary {

    @Id    
    private int id;
    
    @Field(value = "word")
    private String word;

}
