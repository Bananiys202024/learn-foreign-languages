import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';
import { element } from 'protractor';
import { Size } from '../classes/size';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Subscription } from 'rxjs';
import { LoaderService } from '../service/loader.service';
import { LoaderState } from '../loader/LoaderState';
import { Location } from '@angular/common';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css', ],

})
export class DashboardComponent implements OnInit {

  emptyDictionary:string;

  learn:number;
  later:number;
  learned:number;
  
  
  messageSuccess = true;
  timesAddedTenWords:any;

  isLoading: Boolean = false;
  times:string[] = new Array();
  show = false;
  private subscription: Subscription;
		constructor(
	private httpClientService:HttpClientService,
  private router: Router,
  private loaderService: LoaderService,
  private location: Location
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

  this.subscription = this.loaderService.loaderState
  .subscribe((state: LoaderState) => {
    this.show = state.show;
  });
   
  

  }




  ngOnDestroy() {
    this.subscription.unsubscribe();
  }



  training() 
  {
	
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

// this.router.navigate(['/training']);
document.getElementById('GoToDashBoard').click()


}
}

exit()
{
  location.reload();
}



getTenWordsToDictionary() 
{
  this.httpClientService.get10RandomWords().subscribe(
    response => 
    {
      this.times.push('plusOneMessage');
      this.learn+=10;
    }
  );
}



//get 10 words

dismissToastMessage()
{
  this.times.splice(0,1);
}
 //..






}