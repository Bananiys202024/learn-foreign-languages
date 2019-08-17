import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from "@angular/router";
import { Book } from '../classes/book';

@Component({
  selector: 'app-reading-book',
  templateUrl: './reading-book.component.html',
  styleUrls: ['./reading-book.component.css']
})
export class ReadingBookComponent implements OnInit {

  title:string;
  books:string[];
  translatedWord:string; 
  display = -1; //number can't be negative;
  


  constructor(private router: Router,
    private route: ActivatedRoute,
    private httpClientService:HttpClientService) { 

    this.route.params.subscribe(params => {
      this.title = params.title;
     })

  }

  ngOnInit() {
    this.httpClientService.getWord(this.title).subscribe(
      response =>this.handleBookResponse(response),
     );
     
  }

  handleBookResponse(response)
  {
    this.books = response;
  }

  getTranslate(word, counter) 
  {
	 
	this.httpClientService.translateWord(word).subscribe(
	 response =>this.handleTranslateResponse(response),
    );

	this.display=counter;
	
	this.addToDictionary(word);
  }

  
  
  
  handleTranslateResponse(response)
  {
	if(response.answer === ''){
		this.translatedWord = 'untranslated';
	}
	else
	{	
    this.translatedWord = response.answer;  
	}
	
  }
  
  addToDictionary(word)
  {
	this.httpClientService.addToEnglishDictionary(word).subscribe();
  }
  
    resetDisplay()
  {
	this.display=-1;
   }
  

}
