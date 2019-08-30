import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserRegistration } from '../classes/user-registration';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  submitted = false;	
  check_recaptcha = false;

  constructor() { }

  ngOnInit() {
  }

 model = new UserRegistration('name', 'password', 'confirmPassowrd', 'email@gmail.com')
  
 onSubmit(form: any) 
 { 
	console.log(form.controls['name'].value);
	console.log(form.controls['password'].value);
	console.log(form.controls['confirmPassword'].value);
 }
 
 get diagnostic() { return JSON.stringify(this.model); }

 resolved(captchaResponse: string) {
  console.log(`Resolved captcha with response: ${captchaResponse}`);
  this.check_recaptcha =true;
}

}
