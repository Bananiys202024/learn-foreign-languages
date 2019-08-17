import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { HttpClientService} from '../service/http-client.service';
import { Dictionary } from '../classes/dictionary';

@Component({
  selector: 'app-dictionary',
  templateUrl: './dictionary.component.html',
  styleUrls: ['./dictionary.component.css']
})
export class DictionaryComponent implements OnInit {

  dictionaryEnglish: Dictionary []; 
	
  constructor(
		    private router: Router,
		    private route: ActivatedRoute,
			private httpClientService:HttpClientService) { }

  ngOnInit() {
	  
  this.httpClientService.getEnglishDictionary().subscribe(
	 response =>this.handleEnglishDictionaryResponse(response),
   );
         
  }
  
    handleEnglishDictionaryResponse(response)
{
    	console.log(response+"--Response");
	this.dictionaryEnglish = response;
	console.log(this.dictionaryEnglish+"---ThatEnglish");
}
    

}
