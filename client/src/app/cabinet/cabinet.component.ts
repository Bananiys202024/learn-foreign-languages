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
  logged_user_name:string;
  logged_email:string;
  logged_password:string;

  private subscription: Subscription;
  constructor(private httpClientService:HttpClientService, private loaderService: LoaderService) { }
  ngOnInit() {

    this.subscription = this.loaderService.loaderState
    .subscribe((state: LoaderState) => {
      this.show = state.show;
    });

    this.logged_user_name = ', '+localStorage.getItem('username');
    this.logged_email = localStorage.getItem('email');
    this.logged_password = localStorage.getItem('password');

  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  
}
