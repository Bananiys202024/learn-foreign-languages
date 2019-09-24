import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../classes/user';
import { FormsModule } from '@angular/forms';
import { HttpClientService } from '../service/http-client.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { HttpEventType } from '@angular/common/http';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import { FormUserDataService } from '../service/model/form-user-data.service';
import { Preference } from '../classes/preference';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  submitted = false;	
  check_recaptcha = true;
  show = false;
  codeInputed = false;
  emailExist = true
  email_not_exist = true; 

  constructor(private httpClientService:HttpClientService, private router: Router, private authService: AuthService, private form_user_data: FormUserDataService) { }

  ngOnInit() {
    this.emailExist=false;
  }

 model = new User('name', 'password', 'password', 'email@gmail.com', '', new Preference( new Date(), '2', true, 4, '5', '6', '7', '8'));
  
 onSubmit(form: any) 
 {  
    console.log(this.model.email);
    //validation

    //checking by email
    this.httpClientService.check_email(this.model).subscribe(
      response =>
      {


        if(response.includes("User with email:") && response.includes("already exists"))
        {
          this.email_not_exist=false;
          document.getElementById('userExist').click();
        }

        //add if success registration go to confirm password by e-mail;
        if(response.includes("Validation of registration data is ok"))
        {
          this.form_user_data.storage = this.model;
          this.router.navigate(['/registration/confirmCode']);
        }
    
      }
      );


    

  //there add http method
  //pass as method form
  //function: 1. Saving with TTL form
  //function: 2. Check if email that already exist or phone number; 
  //add valdtn

    // this.registration(form); 
 }

 

 resolved(captchaResponse: string) {
  console.log(`Resolved captcha with response: ${captchaResponse}`);
  this.check_recaptcha =true;
}


static MatchPassword(AC: any) {
  const password = AC.get('password').value; // to get value in input tag
  const confirmPassword = AC.get('confirmPassword').value; // to get value in input tag
  if (password !== confirmPassword) {
    return {MatchPassword: true};
  } else {
      return null
  }
}

}
