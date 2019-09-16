import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-tv',
  templateUrl: './tv.component.html',
  styleUrls: ['./tv.component.css']
})
export class TVComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
  }


  back()
  {
    this.location.back();
  }

}
