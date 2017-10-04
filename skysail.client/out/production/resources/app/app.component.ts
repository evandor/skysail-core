import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from './navbar/navbar.component'
import { BreadcrumbModule, PanelMenuModule, MenuItem } from 'primeng/primeng';
import {BackendService} from './services/backend.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [NavbarComponent]
})
export class AppComponent implements OnInit {

  constructor(private _backend: BackendService) {}

  title = 'app';

  items: MenuItem[];

  leftmenuitems: MenuItem[];

  ngOnInit() {
    /*this.items = [];
    this.items.push({ label: 'Categories' });
    this.items.push({ label: 'test', url: 'https://www.skysail.io' });

    this._backend.getLeftMenuItems()
    .subscribe(res => {
      this.leftmenuitems = res;
    }, error => {
      console.log("adding error to alertsService:", error);
    });*/

  }
}
