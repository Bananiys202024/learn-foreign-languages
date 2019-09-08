import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit {

  current_choosen_top_menu_button = this.router.url.replace('/','');
  
  constructor(private router: Router,) { }

  ngOnInit() {
  }


  getAccountDataForCabinet()
  {
    this.router.navigate(['cabinet']);
  }

}
