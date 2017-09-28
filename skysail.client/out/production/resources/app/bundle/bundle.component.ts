import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Observable } from 'rxjs/Observable';
import { BundleDetails } from '../domain/bundleDetails'
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-bundle',
  templateUrl: './bundle.component.html',
  styleUrls: ['./bundle.component.css']
})
export class BundleComponent implements OnInit {

  bundle: BundleDetails;

  private selectedId: number;


  constructor(
    private _backend: BackendService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    console.log("id", id)
    this._backend.getBundleDetails(id)
      .subscribe(res => {
        this.bundle = res;
      });
    /*this.bundle$ = this.route.paramMap
      .switchMap((params: ParamMap) =>
        this._backend.getBundle(params.get('id')));*/

  }

}
