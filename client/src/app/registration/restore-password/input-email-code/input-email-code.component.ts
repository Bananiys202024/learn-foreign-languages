import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-input-email-code',
  templateUrl: './input-email-code.component.html',
  styleUrls: ['./input-email-code.component.css']
})
export class InputEmailCodeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }


  onSubmit(form: any) 
  {  
      this.router.navigate(['/registration/restore-password/new/password']);
  }

}
