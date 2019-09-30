import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../../service/http-client.service';

import { Subscription } from 'rxjs';
import { LoaderService } from '../../service/loader.service';
import { LoaderState } from '../../loader/LoaderState';
import { User } from 'src/app/classes/user';
import { Preference } from 'src/app/classes/preference';
import { NgForm } from '@angular/forms';
import { AdminHttpClientService } from 'src/app/service/http/admin-http-client.service';


@Component({
  selector: 'app-cabinet-admin',
  templateUrl: './cabinet-admin.component.html',
  styleUrls: ['./cabinet-admin.component.css']
})
export class CabinetAdminComponent implements OnInit {

  model = new User('', 'password', '', 'email@gmail.com','', new Preference( new Date(), '2', true, 4, '5', '6', '7', '8'))
  show = false;
  logged_user_name:string;
  logged_email:string;
  logged_password:string;
  logged_role:string;
  user_disabled_successfully:boolean;
  user_disabled_message_successfully:string;
  user_disabled_message_not_found:string;
  user_disabled_not_found_error:boolean;

  private subscription: Subscription;
  constructor(private httpClientService:HttpClientService, private loaderService: LoaderService, private adminHttpClientServie:AdminHttpClientService) { }
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

  



      onSubmit(form: NgForm) {

        console.log(this.model);  

        this.adminHttpClientServie.disableUser(this.model.email).subscribe(
          response =>
         {
           console.log(response+"---Response");
           if(response.includes('All okey, user '+this.model.email+' is disabled'))
           {
            this.user_disabled_message_successfully = response;
            this.user_disabled_successfully = true;
            this.user_disabled_not_found_error = false;

           }

           if(response.includes('User not found'))
           {
            this.user_disabled_message_not_found = response;
            this.user_disabled_not_found_error = true;
            this.user_disabled_successfully = false;

           }

        }
        );
        
          }
     

          
}
