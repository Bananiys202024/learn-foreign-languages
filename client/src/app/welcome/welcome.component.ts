import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.isloginn();
  }


  isloginn(){
    if (localStorage.getItem('token')){
      return true;
    }

    return 'User does not logged in';
  }


  logout()
  {
    console.log('log_out');
    console.log(this.isloginn());
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    this.router.navigate(['']);
  }

}
