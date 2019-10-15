import { Component, OnInit } from '@angular/core';
import { ProgressClient } from '../service/http/common/progress-page/progress-client';

@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.css', '../dashboard/dashboard.component.css', './circle-progress-bar.scss']
})
export class ProgressComponent implements OnInit {

  learned_words:number;
  detected_words:number;
  all_words_dictionary:number;
  learning_words:number;
  current_level_experience:number;
  experience_before_next_level:number;

  experience_earned_today:number;
  current_level:number;
  before_next_level:number;
  how_do_many_days_persistence_of_learning:number;

  constructor(private progressHttpClient:ProgressClient) { }

  ngOnInit() {
    this.progressHttpClient.getProgressData().subscribe(
      response => 
      {
          this.learned_words=response.learned_words;
          this.detected_words=response.detected_words;
          this.all_words_dictionary=response.all_words_dictionary;
          this.learning_words=response.learning_words;
          this.current_level_experience=response.current_level_experience;
          this.experience_before_next_level=response.experience_before_next_level;

          this.experience_earned_today=response.experience_earned_today;
          this.current_level=response.current_level;
          this.before_next_level=response.before_next_level;
          this.how_do_many_days_persistence_of_learning=response.how_do_many_days_persistence_of_learning;

          console.log(this.how_do_many_days_persistence_of_learning+"---1");
          console.log(this.before_next_level+"---2");
          console.log(this.current_level+"---3");
          console.log(this.experience_earned_today+"---4");
      }
     );
  }

}
