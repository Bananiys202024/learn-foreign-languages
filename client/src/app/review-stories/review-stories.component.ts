import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review-books',
  templateUrl: './review-stories.component.html',
  styleUrls: ['./review-stories.component.css', '../dashboard/dashboard.component.css']
})
export class ReviewBooksComponent implements OnInit {

  constructor(	private router: Router) { }

  ngOnInit() {}
  
    getBook(title) 
  {
    title = 'check';
    this.router.navigate(['/readBook', title]);
  }

}
