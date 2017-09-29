import { Component, OnInit } from '@angular/core';
import { MenubarModule, MenuItem } from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  items: MenuItem[];

  constructor() { }

  ngOnInit() {
    this.items = [
      {
        label: 'Home',
        icon: 'fa-home',
        url: 'dashboard'
      },
      {
        label: 'Bundles',
        icon: 'fa-th-large',
        url: 'bundles'
      },
      {
        label: 'Apps',
        icon: 'fa-play-circle',
        url: 'apps'
      },
      {
        label: 'Runtime',
        icon: 'fa-play-circle',
        url: 'maincontent'
      }

    ];
  }

}
