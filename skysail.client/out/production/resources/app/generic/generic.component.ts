import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { Router, ActivatedRoute } from "@angular/router";
import { Observable } from 'rxjs/Observable';

class DummyApp {
  name: String;
  context: String;
  constructor(n: String, c: String) {
    this.name = n;
    this.context = c;
  }
}

@Component({
  selector: 'app-generic',
  templateUrl: './generic.component.html',
  styleUrls: ['./generic.component.css']
})
export class GenericComponent implements OnInit {

  apps: Object[]
  path: Observable<string>

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private _backend: BackendService) {
    this.path = activatedRoute.url.map(segments => segments.join('/'));
  }

  ngOnInit() {
    this.path.subscribe(
      value => {
        console.log ("value = '"+value+"'")
        console.log ("value = '"+value.substring("/maincontent/generic".length)+"'")
        this._backend.getGeneric("/" + value.substring("/maincontent/generic".length))
        .subscribe(res => {
          this.apps = res;
        }, error => {
          console.log("adding error to alertsService...");
        });
      },
      error => console.log(error)
    );
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
