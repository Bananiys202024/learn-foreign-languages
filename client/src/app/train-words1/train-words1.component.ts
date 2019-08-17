import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { HttpClientService} from '../service/http-client.service';
import { TrainWords } from '../classes/train-words';


@Component({
  selector: 'app-train-words1',
  templateUrl: './train-words1.component.html',
  styleUrls: ['./train-words1.component.css']
})
export class TrainWords1Component implements OnInit {

   
  randomcolor = "card-body text-center ";
	
  wordSource:string;
  wordTarget:string;
  countWords: number;
  countProcess: number;
  element:number;
  answers: any []; 
  answer:string;
  check:boolean;
  afterAnswer:boolean;
  beforeAnswer = true;
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
	
	public CheckAnswer(answer)
	{
		this.afterAnswer=true;
		this.beforeAnswer = false;
		
		answer == this.wordTarget ?this.randomcolor += " right ":this.randomcolor += " wrong ";
		
		this.httpClientService.checkAnswerForSecondProcess(answer, this.element, this.reallyId).subscribe();		

		
	}
	
		public nextPage(url)
	{
		this.element = Number('1')+Number(this.element);

		this.router.navigate([url, this.element, this.countWords, this.countProcess]);
	}
	
	
	public CheckAudioAnswer(form: any)
	{	
		
		this.afterAnswer=true;
		this.beforeAnswer = false;
		
		form.controls['answ'].value.toLowerCase() == this.wordSource.toLowerCase() ?this.randomcolor += " right ":this.randomcolor += " wrong ";


		this.httpClientService.getAudioAnswer(form.controls['answ'].value.toLowerCase(), this.element, this.reallyId).subscribe();
	
	}
	

	


	public gotoProductDetails(url, element, countWords, countProcess) {

					//process with element of array;
	    element = Number('1')+Number(element);
		    
		this.router.navigate([url, element, countWords, countProcess]);
}

}
