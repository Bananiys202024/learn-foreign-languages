import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { HttpClientService} from '../service/http-client.service';
 


@Component({
  selector: 'app-train-words5',
  templateUrl: './train-words5.component.html',
  styleUrls: ['./train-words5.component.css']
})
export class TrainWords5Component implements OnInit {

  randomcolor = "card-body text-center ";	
	
  wordSource:string;
  wordTarget:string;
  countWords: number;
  countProcess: number;
  element:number;
  answers: any []; 
  afterAnswer:boolean;
  beforeAnswer = true;
  temporary:string;
  reallyId:number;
  
	constructor(
		    private router: Router,
		    private route: ActivatedRoute,
			private httpClientService:HttpClientService){

    
    this.route.params.subscribe(params => {



		//get element of array;

		this.countWords= params.countWords;
		this.element = params.element;
		this.countProcess = params.countProcess;
    })

	}

 ngOnInit() {
    this.httpClientService.getElementOfArrayForTrainingWords(this.element).subscribe(
	 response =>
	{
	this.wordSource = response.englishWord;
	this.wordTarget = response.russianWord;
	this.reallyId =  response.reallyId;
	}
	);
    
        //tracing errors
	this.httpClientService.tracingErrors().subscribe();
  
        
    if(this.countProcess == 2)
    {
     this.httpClientService.getAnswers(this.element).subscribe(
	 response =>this.handleResponseAnswers(response),
	);
    }
    
  }

 
   handleResponseAnswers(response)
{
	this.answers = response;
}
   
  
  	
  		public getSomeSound(element)
	{
		this.httpClientService.getSomeSound(this.element).subscribe();
	};
	
				public CheckAudioAnswer(form: any)
	{	
		
		this.afterAnswer=true;
		this.beforeAnswer = false;
		
		form.controls['answ'].value.toLowerCase() == this.wordSource.toLowerCase() ?this.randomcolor += " right ":this.randomcolor += " wrong ";
		
		this.httpClientService.getAudioAnswer(form.controls['answ'].value.toLowerCase(), this.element, this.reallyId).subscribe();
	}
	
	
			public CheckAnswer(answer, expected)
	{
		this.afterAnswer=true;
		this.beforeAnswer = false;
		
		answer == expected?this.randomcolor += " right ":this.randomcolor += " wrong ";
		
	    this.httpClientService.checkAnswerForSecondProcess(answer, this.element, this.reallyId).subscribe();

	}
	
		public finalPage(url)
	{
		this.httpClientService.sendWordsToRepeatLater().subscribe();
		this.router.navigate([url]);
	}
	
	
	public gotoProductDetails(url, element, countWords, countProcess) {

			//process with element of array;
		    element= 1;

		//process with counting of current process
		countProcess= Number('1')+Number(countProcess);

		    
		this.router.navigate([url, element, countWords, countProcess]).then( (e) => {
      if (e) {

        console.log("Navigation is successful!"+countWords);
      } else {
        console.log("Navigation has failed!");
	  }

    });
}

}