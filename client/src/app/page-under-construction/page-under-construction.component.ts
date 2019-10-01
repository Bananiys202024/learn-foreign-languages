import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';


@Component({
  selector: 'app-page-under-construction',
  templateUrl: './page-under-construction.component.html',
  styleUrls: ['./page-under-construction.component.css']
})
export class PageUnderConstructionComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
  }



  back()
  {
    this.location.back();
  }

}
