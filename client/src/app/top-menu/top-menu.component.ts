import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit {

  current_choosen_top_menu_button = this.router.url.replace('/','');
  role = localStorage.getItem('role'); 

  constructor(private router: Router,) { }

  ngOnInit() {
  }


  goUserCabinet()
  {
    this.router.navigate(['cabinet']);
  }

  goAdminCabinet()
  {
    this.router.navigate(['admin/cabinet']);
  }
}
