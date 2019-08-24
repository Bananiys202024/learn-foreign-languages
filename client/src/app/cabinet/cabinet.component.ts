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

  
      generateDictionary()
      {

        this.httpClientService.generateDictionary().subscribe(
          response => 
          {
          }
        );
      }

  
}
