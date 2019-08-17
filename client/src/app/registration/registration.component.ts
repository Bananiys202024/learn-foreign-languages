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

  constructor() { }

  ngOnInit() {
  }

 model = new UserRegistration('name', 'password', 'confirmPassowrd')
  
 onSubmit(form: any) 
 { 
	console.log(form.controls['name'].value);
	console.log(form.controls['password'].value);
	console.log(form.controls['confirmPassword'].value);
 }
 
 get diagnostic() { return JSON.stringify(this.model); }


}
