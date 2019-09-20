import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/classes/user';
import { HttpClientService } from '../../service/http-client.service';
import { Router } from '@angular/router';
import { FormUserDataService } from 'src/app/service/model/form-user-data.service';
import { AuthService } from 'src/app/auth.service';


@Component({
  selector: 'app-confirm-code',
  templateUrl: './confirm-code.component.html',
  styleUrls: ['./confirm-code.component.css']
})
export class ConfirmCodeComponent implements OnInit {

  sendedCode:string;
  email = 'BestJavaDeveloper24@gmail.com';
  form:User;

  constructor(private authService: AuthService, private httpClientService:HttpClientService, private router: Router, private form_user_data: FormUserDataService) {
    this.form = this.form_user_data.storage;

   }

  model = new User('', 'Я банан', '', '', '')
  
  ngOnInit() {

    this.authService.send_code_to_email_And_get_code(this.form.email).subscribe(
      (response:string) =>
     {
        console.log('Initialize---'+response);
        this.sendedCode=response;
     }
     );

    console.log("data---"+JSON.stringify(this.form));  
  }

  onSubmit(form: any) 
 {  
   console.log(form.controls['password'].value);
   console.log(this.sendedCode);
    if(form.controls['password'].value === this.sendedCode)
    {
      this.router.navigate(['/registration/success']);
    }  
  
    //validation there
 }


}
