import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AdminHttpClientService {

  constructor(private httpClient:HttpClient) { }

  apiUrl = 'http://localhost:8083/admin/';

        //admin httpClient service 
          disableUser(email: string) {
            return this.httpClient.put(this.apiUrl+'delete/user', email, { responseType: 'text' });      
          }

}
