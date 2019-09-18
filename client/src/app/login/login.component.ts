import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../classes/user';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService,  private router: Router) { }

  model = new User('', 'password', '', 'email@gmail.com','')
  show = false;

  ngOnInit() {
  }

  onSubmit(form: NgForm) {

console.log(this.model);

    this.authService.login(JSON.stringify(this.model))
      .subscribe(res => {
        console.log(res);
        if (res.token) {
          localStorage.setItem('token', res.token);
          localStorage.setItem('email', res.email);
          localStorage.setItem('username', res.username);
          localStorage.setItem('password', res.password);
          localStorage.setItem('role', res.role);
          this.router.navigate(['']);
       }
      }, (err) => {
        console.log(err);
      });



  }

}
