import { Component, OnInit, ElementRef, ChangeDetectionStrategy, OnChanges, Input, Output, EventEmitter } from '@angular/core';

import { NgFor } from '@angular/common';
import { Router } from "@angular/router";

import { BackendService } from '../services/backend.service';
//import { AppGlobalsService } from '../app-globals.service';

import { Bundle } from '../domain/bundle';
//import { BundleStatePipe } from '../bundle-state.pipe';
//import { BundlesFilterPipe } from '../bundles-filter.pipe'
@Component({
  selector: 'app-bundles',
  templateUrl: './bundles.component.html',
  styleUrls: ['./bundles.component.css']
})
export class BundlesComponent implements OnInit {

  bundles: Bundle[];
  searchId = "";
  public searchName: string = '';
  filteredCount: number = 0;

  bundleIdList: string[] = [];

  value: number;
  maxSize: number = 0;
  size: number;

  hidePageHelpFor: string = '';
  alerts: any;

  selectedBundle: Bundle;

  constructor(private router: Router, private _backend: BackendService/*, private _appGlobals: AppGlobalsService*/) {
    //_appGlobals._alerts.subscribe(value => this.alerts = value);
    //_appGlobals._filteredCount.subscribe(value => this.filteredCount = value);
    /*this.hidePageHelpFor = localStorage.getItem('pageHelpBundles');
    if (this.hidePageHelpFor == null) {
      this.hidePageHelpFor = '';
    }*/
  }


  ngOnInit() {
    //this._appGlobals.setIsLoading(true);
    this._backend.getBundles()
      .subscribe(res => {
        this.bundles = res;
        this.bundles.forEach(bundle => {
          this.bundleIdList.push(bundle.id);
          if (bundle.size > this.maxSize) {
            this.maxSize = bundle.size;
          }
        });
        //this._appGlobals.setBundleIdList(this.bundleIdList);
        //this._appGlobals.setIsLoading(false);
      }, error => {
        console.log("adding error to alertsService...");
        //this._alertsService.setError("could not access backend, please check configuration.");
        //this._appGlobals.setAlerts("could not access backend, please check configuration.");
        //this.logError("Error2: " + error);
      });
  }

  onRowSelect(event) {
    this.router.navigate(['/maincontent/bundles/' + event.data.id]);
  }
}
