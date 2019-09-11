import { Component, OnInit } from '@angular/core';
import { ProgressClient } from '../service/progress-client';

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
      }
     );

  }

}
