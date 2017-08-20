import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-apps',
  templateUrl: './apps.component.html',
  styleUrls: ['./apps.component.css']
})
export class AppsComponent implements OnInit {

  apps: Object[]

  constructor(/*private router: Router, */private _backend: BackendService) { }

  ngOnInit() {
    this._backend.getApps()
    .subscribe(res => {
      this.apps = res;
      console.log ("XXX", res);
      console.log ("YYY", res[0]);
      console.log ("ZZZ", Object.getOwnPropertyNames(res[0]));
      /*this.apps.forEach(bundle => {
        this.bundleIdList.push(bundle.id);
      });*/
    }, error => {
      console.log("adding error to alertsService...");
    });
  }

}
