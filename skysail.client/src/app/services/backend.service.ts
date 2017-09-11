import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http'
import { Observable } from 'rxjs/Observable';
import { Bundle } from '../domain/bundle'
import { MenuItem } from '../domain/menuitem'

import 'rxjs/add/operator/map';

@Injectable()
export class BackendService {

  headers = new Headers();

  constructor(private _http: Http/*, private _appGlobals: AppGlobalsService*/) {
    //this._appGlobals._config.subscribe((config) => this.config = config);
    //console.log("base url set to '" + this.config.endpoint + "'");
    //this.headers.append('Authorization', 'Basic d2ViY29uc29sZTp3ZWJjb25zb2xl');
    //this.headers.append('Access-Control-Allow-Origin', '*');
  }

  getBundles(): Observable<Bundle[]> {
    return this._http.get(/*this.config.endpoint + */'/root/bundles', { headers: this.headers })
      .map(res => res.json());
  }

  getApps(): Observable<Object[]> {
    return this._http.get(/*this.config.endpoint + */'/root/apps', { headers: this.headers })
    .map(res => res.json());
  }

  getLeftMenuItems(): Observable<MenuItem[]> {
    return this._http.get(/*this.config.endpoint + */'/root/apps/menus', { headers: this.headers })
    .map(res => res.json());
  }

  getGeneric(path: string): Observable<Object[]> {
    console.log("Backend Call to path: ", path)
    return this._http.get(path, { headers: this.headers })
    .map(res => res.json());
  }

  postGeneric(path: string, payload: string):Observable<Object[]> {
    console.log("Backend Post Call to path: ", path)
    console.log("Backend Post Call with payload: ", payload)
    return this._http.post(path, /*{ headers: this.headers }*/ payload)
      .map(res => res.json());

  }
}
