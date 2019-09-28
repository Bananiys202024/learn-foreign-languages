import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../classes/user';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Preference } from '../classes/preference';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService,  private router: Router) { }

  model = new User('', 'password', '', 'email@gmail.com','', new Preference( new Date(), '2', true, 4, '5', '6', '7', '8'))
  show = false;
  failed_login_invalid_password_email = false;
  message:string;
  title_message:string;
  failed_login_may_be_user_does_not_exist = false;
  failed_login_disabled_user = false;

  ngOnInit() {
  }

  onSubmit(form: NgForm) {

console.log(this.model);

    this.authService.login(JSON.stringify(this.model))
      .subscribe(res => {

        // if(res.includes('User is disabled'))
        // {
        // this.message='Пользователь отключен, похоже вы терорист или просто неприятный тип или даже хуже..поэтому вы не можите войти на веб-сайт';
        // this.title_message='User is disabled';
        // document.getElementById('messageIcon').click();
        // }
        
        // else //start else
        // {
        console.log('We here, but there is...');
        console.log('boolean--'+ typeof res === 'string')
        if(typeof res === "string")
        {
          console.log('rest---'+res);
          console.log('string');
        }

        console.log(res);
        if (res.token) {
          localStorage.setItem('token', res.token);
          localStorage.setItem('email', res.email);
          localStorage.setItem('username', res.username);
          localStorage.setItem('password', res.password);
          localStorage.setItem('role', res.role);
          this.router.navigate(['']);
          }
        }//end else
        
      ,(err) => {
        console.log('Error----'+JSON.stringify(err));
        console.log('Obtained---'+err.error.text);
        if(err.error.text === 'User not found. May be, he does not exist')
        {
          this.failed_login_disabled_user = false;
          this.failed_login_invalid_password_email = false;
          this.failed_login_may_be_user_does_not_exist = true;
        }
        
        if(err.error.text === 'Invalid email/password supplied')
        {
          this.failed_login_disabled_user = false;
          this.failed_login_may_be_user_does_not_exist = false;
          this.failed_login_invalid_password_email=true;
        }

        if(err.error.text === 'User is disabled')
        {
          this.failed_login_disabled_user = true;
          this.failed_login_may_be_user_does_not_exist = false;
          this.failed_login_invalid_password_email= false;
        }

      }
      );



  }

}
