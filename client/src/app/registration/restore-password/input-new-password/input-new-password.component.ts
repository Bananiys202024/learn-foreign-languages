import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-input-new-password',
  templateUrl: './input-new-password.component.html',
  styleUrls: ['./input-new-password.component.css']
})
export class InputNewPasswordComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  onSubmit(form: any) 
  {  
      this.router.navigate(['/registration/success']);
  }

}
