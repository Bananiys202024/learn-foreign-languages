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

  isLogged:boolean;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.isloginn();
  }

  isloginn(){
    if (localStorage.getItem('token'))
    this.isLogged = true;

    if(!localStorage.getItem('token'))
    this.isLogged = false;
  }

  logout()
  {
    this.isLogged=false;
    this.authService.logout();
    this.router.navigate(['']);
  }

}
