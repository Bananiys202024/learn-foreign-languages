import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { NgbCarousel, NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';
import { Router } from "@angular/router";
import { HttpClientService } from '../service/http-client.service';
import { Train } from '../classes/train';

@Component({
  selector: 'app-training-words-slider',
  templateUrl: './training-words-slider.component.html',
  styleUrls: ['./training-words-slider.component.css']
})
export class TrainingWordsSliderComponent implements OnInit {

  upcoming_events = [ 0, 1, 2, 3, 4];
  russianWords:string[] = new Array(5);
  englishWords:string[] = new Array(5);
  generated_array_random_russian_words = ['Крамола','Авось','Империал','Челядь','служанка в доме' ];

  showfirstProcess = true;
  showSecondProcess = false;
  showThirdProcess = false;

  beforeAnswer = true;
  aftereAnswer = false;

  settingColorsBasedOnAnswer = "card-body text-center ";

  id:number;
  currentProcess:number;

  @ViewChild('carousel', {static : true}) carousel: NgbCarousel;

  constructor(private httpClientService:HttpClientService, config: NgbCarouselConfig, private router: Router) {
    // customize default values of carousels used by this component tree
    config.interval = -1;
    config.wrap = true;
    config.keyboard = false;
    config.pauseOnHover = false;
    config.showNavigationIndicators =false;
    config.showNavigationArrows=false;
    config.pauseOnHover = false;
    config.keyboard =false;


  }

  ngOnInit() {
    
    this.httpClientService.initializeTraining().subscribe(
      response =>
     {
     this.russianWords = response.russianWords;
     this.englishWords = response.englishWords;
     }
     );

  }


  //methods related with training words and counting

  understand(e, caro, slideEvent: NgbSlideEvent)
  {
    this.currentProcess=caro.activeId.replace("ngb-slide-4","");

    //checking if we should go to next process
    if(caro.activeId == 'ngb-slide-4')
    {
      this.showfirstProcess = false;
      this.showSecondProcess = true;
      this.showThirdProcess = false;


      this.httpClientService.generate_random_words(this.englishWords[this.currentProcess]).subscribe(
        response =>
       {
       this.generated_array_random_russian_words = response.generated_random_russian_words;
       }
       );
  
    }

    console.log(caro.activeId === 'ngb-slide-3')
    console.log(caro.activeId);
    
    caro.next();
  }

  checkSecondProcessAnswer(answer, e, caro)
  {

    this.beforeAnswer = false;
    this.aftereAnswer = true;

    this.id = caro.activeId.replace('ngb-slide-','');

    answer == this.russianWords[this.id] ?this.settingColorsBasedOnAnswer += " right ":this.settingColorsBasedOnAnswer += " wrong ";
  }

  goToNextPartInSecondProcess(e, caro)
  {

    //checking if we should go to next process
    if(caro.activeId == 'ngb-slide-4')
    {
      this.showfirstProcess = false;
      this.showSecondProcess = false;
      this.showThirdProcess = true;
    }

    this.beforeAnswer = true;
    this.aftereAnswer = false;
    this.settingColorsBasedOnAnswer = "card-body text-center ";

    caro.next();
  }


  checkThirdProcessAnswer(form: any, e, caro)
  {

    this.beforeAnswer = false;
    this.aftereAnswer = true;

    this.id = caro.activeId.replace('ngb-slide-','');

    form.controls['answ'].value.toLowerCase() == this.englishWords[this.id] ?this.settingColorsBasedOnAnswer += " right ":this.settingColorsBasedOnAnswer += " wrong ";
  
    form.reset();
  }




  goToNextPartInThirdProcess(e, caro)
  {

    //checking if we should go to next process
    if(caro.activeId == 'ngb-slide-4')
    {
      this.showfirstProcess = false;
      this.showSecondProcess = false;
      this.showThirdProcess = false;
    
      //redirect to result training words
      this.router.navigate(['/result-training-words']);
    }

    this.beforeAnswer = true;
    this.aftereAnswer = false;
    this.settingColorsBasedOnAnswer = "card-body text-center ";

    caro.next();
  }


//.....





}
