package com.web.Fremdsprache.entity.mongodb;

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
@Document(collection = "ConstGmDict")
public @Data class ConstGmDict {

  @Id    
  private int id;
  
  @Field(value = "word")
  private String word;

}