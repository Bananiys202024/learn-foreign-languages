import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';

import { Subscription } from 'rxjs';
import { LoaderService } from '../service/loader.service';
import { LoaderState } from '../loader/LoaderState';



@Component({
  selector: 'app-cabinet',
  templateUrl: './cabinet.component.html',
  styleUrls: ['./cabinet.component.css']
})
export class CabinetComponent implements OnInit {
  
  show = false;
  private subscription: Subscription;
  constructor(private httpClientService:HttpClientService, private loaderService: LoaderService) { }
  ngOnInit() {
    this.subscription = this.loaderService.loaderState
    .subscribe((state: LoaderState) => {
      this.show = state.show;
    });
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  
      generatEngDictionary()
      {

        this.httpClientService.generate_english_dictionary().subscribe(
          response => 
          {
          }
        );
      }

      generateGermDictionary()
      {

        this.httpClientService.generate_german_dictionary().subscribe(
          response => 
          {
          }
        );
      }

      generateRusnDictionary()
      {

        this.httpClientService.generate_russian_dictionary().subscribe(
          response => 
          {
          }
        );
      }



  
}
