import { Component, OnInit } from '@angular/core';
import { MenubarModule, MenuItem } from 'primeng/primeng';
import {BackendService} from '../services/backend.service'

@Component({
  selector: 'app-leftmenu',
  templateUrl: './leftmenu.component.html',
  styleUrls: ['./leftmenu.component.css']
})
export class LeftmenuComponent implements OnInit {

  leftmenuitems: MenuItem[];

  constructor(private _backend: BackendService) { }

  ngOnInit() {
    this._backend.getLeftMenuItems()
    .subscribe(res => {
      this.leftmenuitems = res;
    }, error => {
      console.log("adding error to alertsService:", error);
    });
  }

}
