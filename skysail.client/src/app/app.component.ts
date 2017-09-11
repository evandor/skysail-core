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
    this.items = [];
    this.items.push({ label: 'Categories' });
    this.items.push({ label: 'test', url: 'https://www.skysail.io' });

    this._backend.getLeftMenuItems()
    .subscribe(res => {
      this.leftmenuitems = res;
    }, error => {
      console.log("adding error to alertsService...");
    });


    /*this.leftmenuitems = [
      {
        label: 'File',
        icon: 'fa-file-o',
        items: [{
          label: 'New',
          icon: 'fa-plus',
          items: [
            { label: 'Project' },
            { label: 'Other' },
          ]
        },
        { label: 'Open' },
        { label: 'Quit' }
        ]
      },
      {
        label: 'Edit',
        icon: 'fa-edit',
        items: [
          { label: 'Undo', icon: 'fa-mail-forward' },
          { label: 'Redo', icon: 'fa-mail-reply' }
        ]
      },
      {
        label: 'Help',
        icon: 'fa-question',
        items: [
          {
            label: 'Contents'
          },
          {
            label: 'Search',
            icon: 'fa-search',
            items: [
              {
                label: 'Text',
                items: [
                  {
                    label: 'Workspace'
                  }
                ]
              },
              {
                label: 'File'
              }
            ]
          }
        ]
      },
      {
        label: 'Actions',
        icon: 'fa-gear',
        items: [
          {
            label: 'Edit',
            icon: 'fa-refresh',
            items: [
              { label: 'Save', icon: 'fa-save' },
              { label: 'Update', icon: 'fa-save' },
            ]
          },
          {
            label: 'Other',
            icon: 'fa-phone',
            items: [
              { label: 'Delete', icon: 'fa-minus' }
            ]
          }
        ]
      }
    ];*/
  }
}