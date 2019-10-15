import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http/common/others/http-client.service';

import { Subscription } from 'rxjs';
import { LoaderService } from '../service/loader/loader.service';
import { LoaderState } from '../loader/LoaderState';
import { User } from '../classes/user';
import { Preference } from '../classes/preference';
import { UserSettingsService } from '../service/http/user/user-settings.service';



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
  time:string;

  powers = ['Europe/Kiev', 'Europe/Berlin', 'Europe/London', 'America/Tijuana', 'Canada/Yukon',
  'Africa/Dakar', 'Asia/Saigon', 'Pacific/Majuro'];
  
  model = new User('name', 'password', 'password', 'email@gmail.com', '', new Preference( new Date(), '2', true, 4, this.powers[0], '6', '7', '8'));


  private subscription: Subscription;
  constructor(private userSettingshttps: UserSettingsService, private httpClientService:HttpClientService, private loaderService: LoaderService) { }
  ngOnInit() {

    this.userSettingshttps.current_time().
    subscribe(res => {
        this.time=res;
    }
    );
    // get current time

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


  changeTimeZone(form: any) 
  {  
     console.log('TimeZones--------'+this.model.preference.timezone);

     this.userSettingshttps.change_timezone_user(this.model.preference.timezone)
      .subscribe(res => {
          console.log('Result='+res);
      }
      );
  }


  
}
