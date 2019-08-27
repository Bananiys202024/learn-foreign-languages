import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { NgbCarousel, NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';
import { Router } from "@angular/router";
import { HttpClientService } from '../service/http-client.service';
import { Train } from '../classes/train';
import { Random } from '../classes/random';


@Component({
  selector: 'app-training-words-slider',
  templateUrl: './training-words-slider.component.html',
  styleUrls: ['./training-words-slider.component.css']
})
export class TrainingWordsSliderComponent implements OnInit {

  upcoming_events = [ 0, 1, 2, 3, 4];
  russianWords:string[] = new Array(5);
  englishWords:string[] = new Array(5);
  generated_array_random_russian_words_1:string[] = new Array(4);
  generated_array_random_russian_words_2:string[] = new Array(4);
  generated_array_random_russian_words_3:string[] = new Array(4);
  generated_array_random_russian_words_4:string[] = new Array(4);
  generated_array_random_russian_words_5:string[] = new Array(4);



  showfirstProcess = true;
  showSecondProcess = false;
  showThirdProcess = false;

  beforeAnswer = true;
  aftereAnswer = false;

  settingColorsBasedOnAnswer = "card-body text-center ";

  id:number;
  currentProcess:string;

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

     this.httpClientService.generate_random_words(this.russianWords).subscribe(
      response =>
     {
       console.log(response);
     this.generated_array_random_russian_words_1 = response.generate_random_words_1;
     this.generated_array_random_russian_words_2 = response.generate_random_words_2;
     this.generated_array_random_russian_words_3 = response.generate_random_words_3;
     this.generated_array_random_russian_words_4 = response.generate_random_words_4;
     this.generated_array_random_russian_words_5 = response.generate_random_words_5;
     console.log(response.generate_random_words_1 +"--Answer")

     console.log(this.russianWords+"--Russian");
     console.log(this.generated_array_random_russian_words_1 +"--array_1" );
     console.log(this.generated_array_random_russian_words_2 +"--array_2" );
     console.log(this.generated_array_random_russian_words_3 +"--array_3" );
     console.log(this.generated_array_random_russian_words_4 +"--array_4" );
     console.log(this.generated_array_random_russian_words_5 +"--array_5" );
     }
     );

    
     }
     );

     

  }


  //methods related with training words and counting

  understand(e, caro, slideEvent: NgbSlideEvent)
  {
    this.currentProcess = caro.activeId;
    console.log(this.currentProcess+"--mArk1");
    console.log(this.generated_array_random_russian_words_1+"--Mark2");

    //checking if we should go to next process
    if(caro.activeId == 'ngb-slide-4')
    {
      this.showfirstProcess = false;
      this.showSecondProcess = true;
      this.showThirdProcess = false;
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
    this.currentProcess = caro.activeId;
    console.log('Messaeg---'+this.currentProcess);
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
