import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { Router } from "@angular/router";
import { Service } from '../domain/service'

@Component({
  selector: 'app-osgi-services',
  templateUrl: './osgi-services.component.html',
  styleUrls: ['./osgi-services.component.css']
})
export class OsgiServicesComponent implements OnInit {

  services: Service[];

  constructor(private router: Router, private _backend: BackendService) { }

  ngOnInit() {
    this._backend.getServices()
    .subscribe(res => {
      this.services = res;
    }, error => {
      console.log("adding error to alertsService...");
    });
  }

}
