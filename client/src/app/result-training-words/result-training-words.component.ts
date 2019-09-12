import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClientService} from '../service/http-client.service';
import { ActivatedRoute } from "@angular/router";
import { Globals } from '../classes/globals'

@Component({
  selector: 'app-result-training-words',
  templateUrl: './result-training-words.component.html',
  styleUrls: ['./result-training-words.component.css']
})
export class ResultTrainingWordsComponent implements OnInit {

  rightAnswers:number;
  wrongAnswers:number;
  countExperience:number;

  constructor(	private router: Router,
    private route: ActivatedRoute,
    private httpClientService:HttpClientService,
    globals: Globals) {

      this.route.params.subscribe(params => {
        //get element of array;
        this.rightAnswers= params.right.replace("right-","");
        this.wrongAnswers = params.wrong.replace("wrong-","");
        })
        this.countExperience = globals.count_experience;  
      }

  ngOnInit() {
  }

public gotoProductDetails(url) {
  this.router.navigate([url, 1, 1, 1]);
}


}
