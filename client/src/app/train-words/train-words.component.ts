import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';
import { element } from 'protractor';
import { Size } from '../classes/size';


@Component({
  selector: 'app-train-words',
  templateUrl: './train-words.component.html',
  styleUrls: ['./train-words.component.css']
})
export class TrainWordsComponent implements OnInit {

  emptyDictionary:string;

  learn:number;
  later:number;
  learned:number;

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
this.router.navigate(['/train-words1', 1,1,1]);
}


}


}