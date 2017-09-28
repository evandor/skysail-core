import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-infotable',
  templateUrl: './infotable.component.html',
  styleUrls: ['./infotable.component.css'],
  inputs: ['key','value']
})
export class InfotableComponent implements OnInit {

  key: String
  value: String

  constructor() { }

  ngOnInit() {
  }

}
