import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../../service/http-client.service';

import { Subscription } from 'rxjs';
import { LoaderService } from '../../service/loader.service';
import { LoaderState } from '../../loader/LoaderState';


@Component({
  selector: 'app-cabinet-admin',
  templateUrl: './cabinet-admin.component.html',
  styleUrls: ['./cabinet-admin.component.css']
})
export class CabinetAdminComponent implements OnInit {

 
  show = false;
  logged_user_name:string;
  logged_email:string;
  logged_password:string;
  logged_role:string;

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
    this.logged_role = localStorage.getItem('role');
    console.log(this.logged_role+"--Role");
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
