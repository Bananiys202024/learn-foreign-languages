import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../classes/user';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService,  private router: Router) { }

  model = new User('email@gmail.com', 'password', '', '')


  ngOnInit() {
  }

  onSubmit(form: NgForm) {
   
console.log(this.model);

    this.authService.login(JSON.stringify(this.model))
      .subscribe(res => {
        console.log(res);
        if (res.token) {
          localStorage.setItem('token', res.token);
          this.router.navigate(['']);
       }
      }, (err) => {
        console.log(err);
      });
  }
  

  
}
