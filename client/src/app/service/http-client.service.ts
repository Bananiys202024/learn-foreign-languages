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
import { Train } from '../classes/train';
import { Random } from '../classes/random';
import { count } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  
 word:TrainWords;
 check:any;
 localhost = 'http://localhost:8083/';

  constructor(
    private httpClient:HttpClient
  ) { 
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
     
                            get10RandomWords()
     {
       return this.httpClient.get('http://localhost:8083/get/10/random/words/to/dictionary');
      } 	


         generate_english_dictionary()
      {
        return this.httpClient.put('http://localhost:8083/account/settings/generate/english/dictionary','');
       } 	
     
         generate_russian_dictionary()
       {
         return this.httpClient.put('http://localhost:8083/account/settings/generate/russian/dictionary','');
        } 

         generate_german_dictionary()
        {
          return this.httpClient.put('http://localhost:8083/account/settings/generate/german/dictionary','');
        } 	


     
      


       //training

               conclusion_training(right_array: string[], wrong_array: string[]) 
      {         
        if(wrong_array.length==0)
        wrong_array.push('null');

        if(right_array.length==0)
        right_array.push('null');

        return this.httpClient.put('http://localhost:8083/training/conclusion/'+right_array+'/'+wrong_array,'');
      }

               initializeTraining()
       {
        return this.httpClient.get<Train>('http://localhost:8083/training/initialize');
       } 	

                generate_random_words(rightAnswers)
       {
        return this.httpClient.get<Random>('http://localhost:8083/training/generate_random_words/'+rightAnswers);
       } 	
       //...


       //login .. registration

       registration(model)
       {
        return this.httpClient.post('http://localhost:8083/api/auth/register', model, { responseType: 'text' });
       } 	

       check_email(model) {
        return this.httpClient.post('http://localhost:8083/api/auth/check/exist/email', model, { responseType: 'text' });      
       }

       //...





}
