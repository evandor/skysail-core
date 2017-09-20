import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { Validators, FormControl, FormGroup, FormBuilder } from '@angular/forms';
//import {Message,SelectItem} from '../../../components/common/api';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  apps: Object[]


  //msgs: Message[] = [];
  userform: FormGroup;
  submitted: boolean;
  //genders: SelectItem[];
  description: string;


  constructor(/*private router: Router, */private _backend: BackendService, private _fb: FormBuilder) { }

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

    this.userform = this._fb.group({
      'firstname': new FormControl('', Validators.required),
      'io.skysail.app.demo.Contact.lastname': new FormControl('', Validators.required),
      'email': new FormControl('', Validators.compose([Validators.required, Validators.minLength(6)])),
      'description': new FormControl('')//,
      //'gender': new FormControl('', Validators.required)
    });

    /*this.genders = [];
    this.genders.push({ label: 'Select Gender', value: '' });
    this.genders.push({ label: 'Male', value: 'Male' });
    this.genders.push({ label: 'Female', value: 'Female' });*/
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

  onSubmit(value: string) {
    this.submitted = true;
    //this.msgs = [];
    // this.msgs.push({severity:'info', summary:'Success', detail:'Form Submitted'});

    var res = this._backend.postGeneric("http://localhost:8080/demo/v1/contacts/new", JSON.stringify(this.userform.value))
    console.log("RES: ", res)

    res.subscribe(
      result => console.log(result),
      error => console.log(error),
  ); 

  }

  get diagnostic() { return JSON.stringify(this.userform.value); }

  getField(col) {
    console.log("WWW", col);
    return col;
  }

}