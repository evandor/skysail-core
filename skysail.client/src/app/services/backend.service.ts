import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http'
import { Observable } from 'rxjs/Observable';
import { Bundle } from '../domain/bundle'
import { BundleDetails } from '../domain/bundleDetails'
import { Service } from '../domain/service'
import { MenuItem } from '../domain/menuitem'

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/of';

@Injectable()
export class BackendService {

  headerDict = {
    //'Content-Type': 'application/json',
    'Accept': 'application/json',
    //'Access-Control-Allow-Headers': 'Content-Type',
  }

  headers = new Headers(this.headerDict);
  options: RequestOptions;

  constructor(private _http: Http/*, private _appGlobals: AppGlobalsService*/) {
    //this._appGlobals._config.subscribe((config) => this.config = config);
    //console.log("base url set to '" + this.config.endpoint + "'");
    //this.headers.append('Authorization', 'Basic d2ViY29uc29sZTp3ZWJjb25zb2xl');
    //this.headers.append('Access-Control-Allow-Origin', '*');
  }

  getBundles(): Observable<Bundle[]> {
    return this._http.get('/root/bundles', { headers: this.headers })
      .map(res => res.json())
      .catch(err => {
        console.log("Backend Error in getBundles:", err);
        return Observable.of([new Bundle("1", "symbolicName"), new Bundle("2", "symbolicName2")])
      })
  }

  getBundleDetails(id: string): Observable<BundleDetails> {
    console.log("calling '/root/bundles/'" + id)
    return this._http.get('/root/bundles/' + id, { headers: this.headers })
      .map(res => res.json())
      .catch(err => {
        console.log("Backend Error in getBundleDetails(id):", err);
        return Observable.of(new BundleDetails())
      })
  }

  getServices(): Observable<Service[]> {
    return this._http.get('/root/services', { headers: this.headers })
      .map(res => res.json())
      .catch(err => {
        console.log("Backend Error in getServices:", err);
        return Observable.of([new Service("1", "objectClass"), new Service("2", "objectClass2")])
      })
  }

  getApps(): Observable<Object[]> {
    return this._http.get(/*this.config.endpoint + */'/root/apps', { headers: this.headers })
      .map(res => res.json());
  }

  getLeftMenuItems(): Observable<MenuItem[]> {
    return this._http.get('/root/apps/menus', { headers: this.headers })
      .map(res => res.json())
      .catch(err => {
        console.log("Backend Error in getLeftMenuItems:", err);
        return Observable.of([new MenuItem('Bundles', 'fa-th-large', '/maincontent/bundles'), new MenuItem('Services', 'fa-play-circle', '/maincontent/services')])
      })
  }

  getGeneric(path: string): Observable<Object[]> {
    console.log("Backend Call to path: ", path)
    return this._http.get(path, { headers: this.headers })
      .map(res => res.json());
  }

  postGeneric(path: string, payload: string): Observable<Object[]> {
    console.log("Backend Post Call to path ", path)
    console.log("Backend Post Call with payload ", payload)


    this.headers = new Headers({
      'Content-Type': 'application/json',
      'Accept': 'q=0.8;application/json;q=0.9'
    });
    this.options = new RequestOptions({ headers: this.headers });

    return this._http.post(path, payload, this.options)
      .map(res => res.json())
      .catch(this.handleError);
  }

  private handleError(error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
