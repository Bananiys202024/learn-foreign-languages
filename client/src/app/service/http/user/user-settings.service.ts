import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UserSettingsService {

  apiUrl = 'http://localhost:8083/user/';

  constructor(private httpClient:HttpClient) { }

  //admin httpClient service 
  change_timezone_user(timezone: string) {
      return this.httpClient.put(this.apiUrl+'settings/timezone', timezone, { responseType: 'text' });      
  }

  current_time() {
    return this.httpClient.get(this.apiUrl+'get/timezone', { responseType: 'text' });      
  }

}
