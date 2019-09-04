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


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  submitted = false;	
  check_recaptcha = false;

  constructor(private httpClientService:HttpClientService, private router: Router, private authService: AuthService) { }

  ngOnInit() {
  }

 model = new User('name', 'password', 'confirmPassowrd', 'email@gmail.com')
  
 onSubmit(form: any) 
 { 
//	console.log(form.controls['name'].value);
//	console.log(form.controls['password'].value);
//  console.log(form.controls['confirmPassword'].value);

//add validation


this.httpClientService.registration(this.model).subscribe(
  response =>
  {
        console.log(response);
    if(response.includes("User with email:") && response.includes("already exists"))
    {
      document.getElementById('userExist').click();
    }

    //add if success registration go to confirm password by e-mail;
    
  }
  );
 
}


 get diagnostic() { return JSON.stringify(this.model); }

 resolved(captchaResponse: string) {
  console.log(`Resolved captcha with response: ${captchaResponse}`);
  this.check_recaptcha =true;
}

}
