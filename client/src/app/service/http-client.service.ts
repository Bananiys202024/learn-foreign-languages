import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

//classes
import { TrainWords } from '../classes/train-words';
import { Answers } from '../classes/answers';
import { Mistakes } from '../classes/mistakes';
import { Book } from '../classes/book';
import { Boolean } from '../classes/boolean';
import { Dictionary } from '../classes/dictionary';
import { Size } from '../classes/size';
import { CounterExperience } from '../classes/counter-experience';




@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

 word:TrainWords;
 check:any;


  constructor(
    private httpClient:HttpClient
  ) { 
     }

	   getTrainWords()
  {
    return this.httpClient.get('http://localhost:8083/trainWords/initializeList');
  }


  	     getSomeSound(element)
  {	    	 
    return this.httpClient.get('http://localhost:8083/speach/'+element);
  }
  	
      getAnswers(element)
  {	    	 
    return this.httpClient.get<Answers[]>('http://localhost:8083/secondProcess/TrainingWord/generate/list/answers/russian/'+element);
  }
      
      getAudioAnswer(inputed, element, reallyId)
  {	   	         	
    return this.httpClient.get('http://localhost:8083/thirdProcess/checkAudioAnswer/'+inputed+'/'+element+"/"+reallyId);
  }


  checkAnswerForSecondProcess(inputed, element, reallyId)
  {	   	   
    return this.httpClient.get('http://localhost:8083/secondProcess/checkAnswer/'+inputed+'/'+element+"/"+reallyId);
  }

   getElementOfArrayForTrainingWords(id)
  {
    return this.httpClient.get<TrainWords>('http://localhost:8083/get/entity/'+id);
  }

   getMistakes()
   {
	    return this.httpClient.get<Mistakes>('http://localhost:8083/trainingWords/getMiistakes');
   }
   
      getWord(title)
   {
	    return this.httpClient.get<Book>('http://localhost:8083/getStory/'+title);
   }
      
      translateWord(word)
   {
	    return this.httpClient.get<Answers>('http://localhost:8083/translate/'+word);
   }  
      
           addToEnglishDictionary(word)
   {
	    return this.httpClient.get<Answers>('http://localhost:8083/Dictionary/English/'+word);
   }  
     
                  checkIfDictionaryEmpty()
   {
	    return this.httpClient.get<Boolean>('http://localhost:8083/Dictionary/English/empty');
   } 
                  
                    getEnglishDictionary()
   {
	    return this.httpClient.get<Dictionary>('http://localhost:8083/get/Dictionary/English');
   } 
           
   					getSizeEnglishDictionary()
    {
	    return this.httpClient.get<Size>('http://localhost:8083/get/Dictionary/size');
   	} 
   					
   	   					sendWordsToRepeatLater()
    {
	    return this.httpClient.get('http://localhost:8083/words/onRepeat/before/tomorrow');
   	} 				
   			
	   	   					cleanResources()
    {
	    return this.httpClient.get('http://localhost:8083/trainWords/destroyList');
   	} 		
   	   					
	  	   	   					tracingErrors()
    {
	    return this.httpClient.get('http://localhost:8083/empty/controller/only/for/tracing/errors');
   	} 			   					
	   	   					
      	  	   	   					getCounterOfExperience()
    {
	    return this.httpClient.get<number>('http://localhost:8083/count/counter/experience/training/words');
   	} 	
      
}