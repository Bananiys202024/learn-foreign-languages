import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';
import { element } from 'protractor';
import { Size } from '../classes/size';
import { trigger, state, style, animate, transition } from '@angular/animations';


@Component({
  selector: 'app-train-words',
  templateUrl: './train-words.component.html',
  styleUrls: ['./train-words.component.css', ],
})
export class TrainWordsComponent implements OnInit {

  emptyDictionary:string;

  learn:number;
  later:number;
  learned:number;

  messageSuccess = true;
  timesAddedTenWords:any;

		constructor(
	private httpClientService:HttpClientService,
	private router: Router
  ) {}

  ngOnInit() {

  this.httpClientService.getSizeEnglishDictionary().subscribe(
	 response => 
	 {
		 this.learn = response.WordsOnLearn;
		 this.later = response.WordsOnRepeat;
		 this.learned = response.Learned;
	 }
  );
	 
  }

public gotoProductDetails() {
	
 this.httpClientService.checkIfDictionaryEmpty().subscribe(
	 response =>this.handleEmptyDictionary(response),
  );

}


handleEmptyDictionary(response) {

	this.emptyDictionary = response.bool;
	
if(this.emptyDictionary)
{
	document.getElementById('modalClick').click()
}
else
{

this.httpClientService.getTrainWords().subscribe();
//this.router.navigate(['/train-words1', 1,1,1]);
this.router.navigate(['/train-words-slider']);

}
}



getTenWordsToDictionary() 
{
  console.log('Added 10 words');
}



//get 10 words

appearAndFadeOut()
{
this.messageSuccess = false;
}
 //..





}