import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/classes/user';
import { HttpClientService } from '../../service/http-client.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-confirm-code',
  templateUrl: './confirm-code.component.html',
  styleUrls: ['./confirm-code.component.css']
})
export class ConfirmCodeComponent implements OnInit {

  sendedCode = '555';
  email = 'BestJavaDeveloper24@gmail.com';

  constructor(private httpClientService:HttpClientService, private router: Router) { }

  model = new User('', 'Я банан', '', '', '')
  
  ngOnInit() {
  }

  onSubmit(form: any) 
 {  
   console.log(form.controls['password'].value);
    if(form.controls['password'].value === this.sendedCode)
    {
      this.router.navigate(['/registration/success']);
    }  
  
    //validation there
 }


}
