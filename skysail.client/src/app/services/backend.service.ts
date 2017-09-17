import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http'
import { Observable } from 'rxjs/Observable';
import { Bundle } from '../domain/bundle'
import { MenuItem } from '../domain/menuitem'

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/of';

@Injectable()
export class BackendService {

  headers = new Headers();
  options: RequestOptions;

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
    return this._http.get('/root/apps/menus', { headers: this.headers })
      .map(res => res.json())
      .catch(err => { 
        console.log(err);
        return Observable.of([new MenuItem('Apps','fa-box','/maincontent/sub'),new MenuItem('Dashboard','fa-box','/maincontent/sub2')])
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
