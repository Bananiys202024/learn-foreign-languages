import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BanchProgressData } from 'src/app/classes/banch-progress-data';

@Injectable({
    providedIn: 'root'
  })
export class ProgressClient {

    constructor(private httpClient:HttpClient) {}

      //get learned words
      //get detected words
      //get learning words
      //get dictionary words
      //get current level
      //get How do many experience we need for next level
      getProgressData()
      {
       return this.httpClient.get<BanchProgressData>('http://localhost:8083/account/settings/banch/data/for/progress/page');
      } 	

       //query to redis
       //to get 
       //How do many days we training yourself? Set in redis expired date after 5 days.

}
