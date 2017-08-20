import { Component, OnInit } from '@angular/core';
import { MenubarModule, MenuItem } from 'primeng/primeng';

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
        label: 'Bundles',
        icon: 'fa-th-large',
        url: 'bundles'
      },
      {
        label: 'Services',
        icon: 'fa-play-circle',
        url: 'services'
      },
      {
        label: 'Apps',
        icon: 'fa-play-circle',
        url: 'apps'
      }
    ];
  }

}
