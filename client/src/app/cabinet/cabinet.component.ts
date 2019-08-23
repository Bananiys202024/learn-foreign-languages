import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';

@Component({
  selector: 'app-cabinet',
  templateUrl: './cabinet.component.html',
  styleUrls: ['./cabinet.component.css']
})
export class CabinetComponent implements OnInit {

  constructor(	private httpClientService:HttpClientService) { }

  ngOnInit() {
  }



  

      generateDictionary()
      {

        this.httpClientService.generateDictionary().subscribe(
          response => 
          {
          }
        );
      }

  
}
