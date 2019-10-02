import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/classes/user';
import { HttpClientService } from '../../service/http-client.service';
import { Router } from '@angular/router';
import { FormUserDataService } from 'src/app/service/model/form-user-data.service';
import { AuthService } from 'src/app/auth.service';
import { Preference } from 'src/app/classes/preference';


@Component({
  selector: 'app-confirm-code',
  templateUrl: './confirm-code.component.html',
  styleUrls: ['./confirm-code.component.css']
})
export class ConfirmCodeComponent implements OnInit {

  sendedCode:string;
  email = 'BestJavaDeveloper24@gmail.com';
  form:User;
  wrong_code = false;

  constructor(private authService: AuthService, private httpClientService:HttpClientService, private router: Router, private form_user_data: FormUserDataService) {
    this.form = this.form_user_data.storage;

   }

  model = new User('', 'Я банан', '', '', '', new Preference( new Date(), '2', true, 4, '5', '6', '7', '8'))
  
  ngOnInit() {

    this.authService.send_code_to_email_And_get_code(this.form.email).subscribe(
      response =>
     {
        console.log('Initialize---'+response);
        this.sendedCode=response;
     }
     );

    console.log("data---"+JSON.stringify(this.form));  
  }

  onSubmit(form: any) 
 {  

    if(form.controls['password'].value != this.sendedCode) //there is exclamation sign, be carefule, be in attantion;
    {
      this.wrong_code = true;
    }

    //inputed code equals by sended code
    if(form.controls['password'].value === this.sendedCode)
    {
      
      this.httpClientService.registration(this.form).subscribe(
        response =>
        {
              console.log(response);
          if(response.includes("User with email:") && response.includes("already exists"))
          {
            document.getElementById('userExist').click();
          }
      
          //add if success registration go to confirm password by e-mail;
          if(response.includes("User registered successfully"))
          {
            this.router.navigate(['/registration/success']);
          }
      
        }
        );

      
    }  
  
    //validation there
 }

 


}
