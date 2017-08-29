import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  
    apps: Object[]
  
    constructor(/*private router: Router, */private _backend: BackendService) { }
  
    ngOnInit() {
      this._backend.getApps()
        .subscribe(res => {
          this.apps = res;
          console.log("XXX", res);
          console.log("YYY", res[0]);
          console.log("ZZZ", Object.getOwnPropertyNames(res[0]));
          /*this.apps.forEach(bundle => {
            this.bundleIdList.push(bundle.id);
          });*/
        }, error => {
          console.log("adding error to alertsService...");
          this.apps = new Array();
          //this.apps.push(new DummyApp('root', '/root'))
        });
    }
  
    getColumns() {
      var columns = new Set<String>();
      if (this.apps == null) {
        return columns;
      }
      this.apps.forEach(app => {
        var cols = Object.getOwnPropertyNames(app)
        cols.forEach(c => columns.add(c));
      });
      return columns;
      //return new Array("name", "context")
    }
  
    getField(col) {
      console.log("WWW",col);
      return col;
    }
  
  }