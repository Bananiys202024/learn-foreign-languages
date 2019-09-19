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


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  submitted = false;	
  check_recaptcha = false;
  show = false;
  codeInputed = false;

  constructor(private httpClientService:HttpClientService, private router: Router, private authService: AuthService, private form_user_data: FormUserDataService) { }

  ngOnInit() {
  }

 model = new User('name', 'password', 'confirmPassowrd', 'email@gmail.com', '')
  
 onSubmit(form: any) 
 {  
    
    //validation

    this.form_user_data.storage = this.model;
    this.router.navigate(['/registration/confirmCode']);

  //there add http method
  //pass as method form
  //function: 1. Saving with TTL form
  //function: 2. Check if email that already exist or phone number; 


    // this.registration(form); 
 }

  registration(form: any) {
   
//add validation
// form.controls['name'].value

console.log(this.model);
this.httpClientService.registration(this.model).subscribe(
  response =>
  {
        console.log(response);
    if(response.includes("User with email:") && response.includes("already exists"))
    {
      document.getElementById('userExist').click();
    }

    //add if success registration go to confirm password by e-mail;
    if(response.includes("User registered successfully"))
    {
      this.router.navigate(['/login']);
    }

  }
  );

  }


 get diagnostic() { return JSON.stringify(this.model); }

 resolved(captchaResponse: string) {
  console.log(`Resolved captcha with response: ${captchaResponse}`);
  this.check_recaptcha =true;
}

}
