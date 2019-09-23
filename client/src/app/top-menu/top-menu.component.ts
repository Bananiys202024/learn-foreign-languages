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
  page_choosen = false;
  new_messages = 1;

  constructor(private router: Router,) { }

  ngOnInit() {
    if(this.current_choosen_top_menu_button.includes('cabinet'))
    this.page_choosen=true;
  }


  goCabinet()
  {
    if(this.role === 'User')
    this.router.navigate(['user/cabinet']);

    if(this.role === 'Admin')
    this.router.navigate(['admin/cabinet']);
  }

  displayMessages()
  {
    this.router.navigate(['user/messages']);
  }

}
