webpackJsonp(["main"],{

/***/ "../../../../../src/$$_gendir lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	return new Promise(function(resolve, reject) { reject(new Error("Cannot find module '" + req + "'.")); });
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "../../../../../src/$$_gendir lazy recursive";

/***/ }),

/***/ "../../../../../src/app/app-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppRoutingModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__apps_apps_component__ = __webpack_require__("../../../../../src/app/apps/apps.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__bundles_bundles_component__ = __webpack_require__("../../../../../src/app/bundles/bundles.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__dashboard_dashboard_component__ = __webpack_require__("../../../../../src/app/dashboard/dashboard.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





var routes = [
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: __WEBPACK_IMPORTED_MODULE_4__dashboard_dashboard_component__["a" /* DashboardComponent */] },
    { path: 'apps', component: __WEBPACK_IMPORTED_MODULE_2__apps_apps_component__["a" /* AppsComponent */] },
    { path: 'bundles', component: __WEBPACK_IMPORTED_MODULE_3__bundles_bundles_component__["a" /* BundlesComponent */] },
    { path: 'services', component: __WEBPACK_IMPORTED_MODULE_2__apps_apps_component__["a" /* AppsComponent */] }
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    return AppRoutingModule;
}());
AppRoutingModule = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        imports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["RouterModule"].forRoot(routes)],
        exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["RouterModule"]]
    })
], AppRoutingModule);

//# sourceMappingURL=app-routing.module.js.map

/***/ }),

/***/ "../../../../../src/app/app.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<navbar></navbar>\n<br><br><br>\n<div class=\"row\">\n  <div class=\"col-md-1\">&nbsp;</div>\n  <div class=\"col-md-9\">\n    <router-outlet></router-outlet>\n  </div>\n  <div class=\"col-md-2\">\n    <!--snapshot nav.-->\n\n  </div>\n</div>\n<div class=\"container\">\n  <!--<footer></footer>-->\n</div>"

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__navbar_navbar_component__ = __webpack_require__("../../../../../src/app/navbar/navbar.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


var AppComponent = (function () {
    function AppComponent() {
        this.title = 'app';
    }
    return AppComponent;
}());
AppComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-root',
        template: __webpack_require__("../../../../../src/app/app.component.html"),
        styles: [__webpack_require__("../../../../../src/app/app.component.css")],
        providers: [__WEBPACK_IMPORTED_MODULE_1__navbar_navbar_component__["a" /* NavbarComponent */]]
    })
], AppComponent);

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/@angular/platform-browser.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__navbar_navbar_component__ = __webpack_require__("../../../../../src/app/navbar/navbar.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__apps_apps_component__ = __webpack_require__("../../../../../src/app/apps/apps.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__app_routing_module__ = __webpack_require__("../../../../../src/app/app-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__services_backend_service__ = __webpack_require__("../../../../../src/app/services/backend.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__bundles_bundles_component__ = __webpack_require__("../../../../../src/app/bundles/bundles.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_primeng_primeng__ = __webpack_require__("../../../../primeng/primeng.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_primeng_primeng___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_primeng_primeng__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__dashboard_dashboard_component__ = __webpack_require__("../../../../../src/app/dashboard/dashboard.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};











var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["NgModule"])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_2__app_component__["a" /* AppComponent */],
            __WEBPACK_IMPORTED_MODULE_3__navbar_navbar_component__["a" /* NavbarComponent */],
            __WEBPACK_IMPORTED_MODULE_4__apps_apps_component__["a" /* AppsComponent */],
            __WEBPACK_IMPORTED_MODULE_7__bundles_bundles_component__["a" /* BundlesComponent */],
            __WEBPACK_IMPORTED_MODULE_10__dashboard_dashboard_component__["a" /* DashboardComponent */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["BrowserModule"],
            //    FormsModule,
            __WEBPACK_IMPORTED_MODULE_9__angular_http__["c" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_8_primeng_primeng__["AccordionModule"],
            __WEBPACK_IMPORTED_MODULE_8_primeng_primeng__["TabViewModule"],
            __WEBPACK_IMPORTED_MODULE_8_primeng_primeng__["MenuModule"],
            __WEBPACK_IMPORTED_MODULE_8_primeng_primeng__["MenubarModule"],
            __WEBPACK_IMPORTED_MODULE_8_primeng_primeng__["DataTableModule"], __WEBPACK_IMPORTED_MODULE_8_primeng_primeng__["SharedModule"],
            __WEBPACK_IMPORTED_MODULE_5__app_routing_module__["a" /* AppRoutingModule */],
            //    CommonModule,
            __WEBPACK_IMPORTED_MODULE_5__app_routing_module__["a" /* AppRoutingModule */],
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["BrowserModule"]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_6__services_backend_service__["a" /* BackendService */]],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_2__app_component__["a" /* AppComponent */]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ "../../../../../src/app/apps/apps.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/apps/apps.component.html":
/***/ (function(module, exports) {

module.exports = "<h2><i class=\"fa fa-th-large\" aria-hidden=\"true\" style=\"color: green\"></i> Apps:</h2>\n\n<p-dataTable [value]=\"apps\" [rows]=\"20\" [paginator]=\"true\" [pageLinks]=\"5\" [rowsPerPageOptions]=\"[10,20,50,100]\">\n  <!--<ng-template ngFor let-col [ngForOf]=\"getColumns()\">\n    <p-column [field]=\"col\" [header]=\"col\"></p-column>\n  </ng-template>-->\n  <ng-template ngFor let-col [ngForOf]=\"getColumns()\">\n    <p-column [field]=\"col\" [header]=\"col\">\n      <ng-template let-col let-car=\"rowData\" let-ri=\"rowIndex\" pTemplate=\"body\">\n        <span><a href='/client{{car[\"context\"]}}'>{{car[col.field]}}</a></span>\n      </ng-template>\n    </p-column>\n  </ng-template>\n  <p-column field=\"name\" header=\"Raw\">\n    <ng-template let-col let-car=\"rowData\" let-ri=\"rowIndex\" pTemplate=\"body\">\n      <span><a href='/root{{car[\"context\"]}}'>{{car[col.field]}}</a></span>\n    </ng-template>\n  </p-column>\n  <!--<p-column field=\"color\" header=\"Color\">\n    <ng-template let-col let-car=\"rowData\" pTemplate=\"body\">\n      <span [style.color]=\"car[col.field]\">{{car[col.field]}}</span>\n    </ng-template>\n  </p-column>\n  <p-column styleClass=\"col-button\">\n    <ng-template pTemplate=\"header\">\n      <button type=\"button\" pButton icon=\"fa-refresh\"></button>\n    </ng-template>\n    <ng-template let-car=\"rowData\" pTemplate=\"body\">\n      <button type=\"button\" pButton (click)=\"selectCar(car)\" icon=\"fa-search\"></button>\n    </ng-template>\n  </p-column>-->\n</p-dataTable>"

/***/ }),

/***/ "../../../../../src/app/apps/apps.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_backend_service__ = __webpack_require__("../../../../../src/app/services/backend.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var DummyApp = (function () {
    function DummyApp(n, c) {
        this.name = n;
        this.context = c;
    }
    return DummyApp;
}());
var AppsComponent = (function () {
    function AppsComponent(/*private router: Router, */ _backend) {
        this._backend = _backend;
    }
    AppsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._backend.getApps()
            .subscribe(function (res) {
            _this.apps = res;
            console.log("XXX", res);
            console.log("YYY", res[0]);
            console.log("ZZZ", Object.getOwnPropertyNames(res[0]));
            /*this.apps.forEach(bundle => {
              this.bundleIdList.push(bundle.id);
            });*/
        }, function (error) {
            console.log("adding error to alertsService...");
            _this.apps = new Array();
            _this.apps.push(new DummyApp('root', '/root'));
        });
    };
    AppsComponent.prototype.getColumns = function () {
        var columns = new Set();
        if (this.apps == null) {
            return columns;
        }
        this.apps.forEach(function (app) {
            var cols = Object.getOwnPropertyNames(app);
            cols.forEach(function (c) { return columns.add(c); });
        });
        return columns;
        //return new Array("name", "context")
    };
    AppsComponent.prototype.getField = function (col) {
        console.log("WWW", col);
        return col;
    };
    return AppsComponent;
}());
AppsComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-apps',
        template: __webpack_require__("../../../../../src/app/apps/apps.component.html"),
        styles: [__webpack_require__("../../../../../src/app/apps/apps.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */]) === "function" && _a || Object])
], AppsComponent);

var _a;
//# sourceMappingURL=apps.component.js.map

/***/ }),

/***/ "../../../../../src/app/bundles/bundles.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/bundles/bundles.component.html":
/***/ (function(module, exports) {

module.exports = "<h2><i class=\"fa fa-th-large\" aria-hidden=\"true\" style=\"color: green\"></i> Bundle <i>bundle.symbolicName</i>:</h2>\n\n\n<p-tabView>\n  <p-tabPanel header=\"Bundles Overview\">\n    app-errors../app-errors\n    <p-dataTable [value]=\"bundles\" [rows]=\"20\" [paginator]=\"true\" [pageLinks]=\"5\" [rowsPerPageOptions]=\"[10,20,50,100]\">\n      <p-column field=\"id\" header=\"ID\" [style]=\"{'width':'40px','font-weight': 'bold'}\"></p-column>\n      <p-column field=\"symbolicName\" \n                header=\"Symbolic Name\" \n                [sortable]=\"true\" \n                [filter]=\"true\" filterPlaceholder=\"Search\" filterMatchMode=\"contains\" \n                [style]=\"{'width':'40%'}\">\n      </p-column>\n      <p-column field=\"providedServices\" header=\"# prov. Serv.\"></p-column>\n      <p-column field=\"usedServices\" header=\"# used Serv.\"></p-column>\n      <p-column field=\"version\" header=\"Version\" [style]=\"{'width':'180px'}\"></p-column>\n      <p-column field=\"status\" header=\"Status\"></p-column>\n      <p-column field=\"size\" header=\"Size\" [sortable]=\"true\"></p-column>\n    </p-dataTable>\n  </p-tabPanel>\n  <p-tabPanel header=\"Service Dependencies\">\n    app-errors../app-errors Content 3\n  </p-tabPanel>\n  <p-tabPanel header=\"Package Dependencies\">\n    app-errors../app-errors Content 3\n  </p-tabPanel>\n</p-tabView>"

/***/ }),

/***/ "../../../../../src/app/bundles/bundles.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BundlesComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_backend_service__ = __webpack_require__("../../../../../src/app/services/backend.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



//import { BundleStatePipe } from '../bundle-state.pipe';
//import { BundlesFilterPipe } from '../bundles-filter.pipe'
var BundlesComponent = (function () {
    function BundlesComponent(router, _backend /*, private _appGlobals: AppGlobalsService*/) {
        this.router = router;
        this._backend = _backend; /*, private _appGlobals: AppGlobalsService*/
        this.searchId = "";
        this.searchName = '';
        this.filteredCount = 0;
        this.bundleIdList = [];
        this.maxSize = 0;
        this.hidePageHelpFor = '';
        //_appGlobals._alerts.subscribe(value => this.alerts = value);
        //_appGlobals._filteredCount.subscribe(value => this.filteredCount = value);
        /*this.hidePageHelpFor = localStorage.getItem('pageHelpBundles');
        if (this.hidePageHelpFor == null) {
          this.hidePageHelpFor = '';
        }*/
    }
    BundlesComponent.prototype.ngOnInit = function () {
        var _this = this;
        //this._appGlobals.setIsLoading(true);
        this._backend.getBundles()
            .subscribe(function (res) {
            _this.bundles = res;
            _this.bundles.forEach(function (bundle) {
                _this.bundleIdList.push(bundle.id);
                if (bundle.size > _this.maxSize) {
                    _this.maxSize = bundle.size;
                }
            });
            //this._appGlobals.setBundleIdList(this.bundleIdList);
            //this._appGlobals.setIsLoading(false);
        }, function (error) {
            console.log("adding error to alertsService...");
            //this._alertsService.setError("could not access backend, please check configuration.");
            //this._appGlobals.setAlerts("could not access backend, please check configuration.");
            //this.logError("Error2: " + error);
        });
    };
    return BundlesComponent;
}());
BundlesComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-bundles',
        template: __webpack_require__("../../../../../src/app/bundles/bundles.component.html"),
        styles: [__webpack_require__("../../../../../src/app/bundles/bundles.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["Router"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_backend_service__["a" /* BackendService */] /*, private _appGlobals: AppGlobalsService*/ !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_backend_service__["a" /* BackendService */] /*, private _appGlobals: AppGlobalsService*/) === "function" && _b || Object])
], BundlesComponent);

var _a, _b;
//# sourceMappingURL=bundles.component.js.map

/***/ }),

/***/ "../../../../../src/app/dashboard/dashboard.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/dashboard/dashboard.component.html":
/***/ (function(module, exports) {

module.exports = "<h2><i class=\"fa fa-th-large\" aria-hidden=\"true\" style=\"color: green\"></i> Dashboard:</h2>\n\n<p-dataTable [value]=\"apps\" [rows]=\"20\" [paginator]=\"true\" [pageLinks]=\"5\" [rowsPerPageOptions]=\"[10,20,50,100]\">\n  <p-column field=\"name\" header=\"Name\">\n    <ng-template let-col let-car=\"rowData\" let-ri=\"rowIndex\" pTemplate=\"body\">\n      <span><a href='/client{{car[\"context\"]}}'>{{car[col.field]}}</a></span>\n    </ng-template>\n  </p-column>\n  <p-column field=\"context\" header=\"Context\"></p-column>\n  <p-column field=\"description\" header=\"Description\"></p-column>\n</p-dataTable>"

/***/ }),

/***/ "../../../../../src/app/dashboard/dashboard.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DashboardComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_backend_service__ = __webpack_require__("../../../../../src/app/services/backend.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var DashboardComponent = (function () {
    function DashboardComponent(/*private router: Router, */ _backend) {
        this._backend = _backend;
    }
    DashboardComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._backend.getApps()
            .subscribe(function (res) {
            _this.apps = res;
            console.log("XXX", res);
            console.log("YYY", res[0]);
            console.log("ZZZ", Object.getOwnPropertyNames(res[0]));
            /*this.apps.forEach(bundle => {
              this.bundleIdList.push(bundle.id);
            });*/
        }, function (error) {
            console.log("adding error to alertsService...");
            _this.apps = new Array();
            //this.apps.push(new DummyApp('root', '/root'))
        });
    };
    DashboardComponent.prototype.getColumns = function () {
        var columns = new Set();
        if (this.apps == null) {
            return columns;
        }
        this.apps.forEach(function (app) {
            var cols = Object.getOwnPropertyNames(app);
            cols.forEach(function (c) { return columns.add(c); });
        });
        return columns;
        //return new Array("name", "context")
    };
    DashboardComponent.prototype.getField = function (col) {
        console.log("WWW", col);
        return col;
    };
    return DashboardComponent;
}());
DashboardComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-dashboard',
        template: __webpack_require__("../../../../../src/app/dashboard/dashboard.component.html"),
        styles: [__webpack_require__("../../../../../src/app/dashboard/dashboard.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */]) === "function" && _a || Object])
], DashboardComponent);

var _a;
//# sourceMappingURL=dashboard.component.js.map

/***/ }),

/***/ "../../../../../src/app/navbar/navbar.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/navbar/navbar.component.html":
/***/ (function(module, exports) {

module.exports = "<p-menubar [model]=\"items\">\n  <!--<input type=\"text\" pInputText placeholder=\"Search\">\n  <button pButton label=\"Logout\" icon=\"fa-sign-out\"></button>-->\n</p-menubar>"

/***/ }),

/***/ "../../../../../src/app/navbar/navbar.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NavbarComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var NavbarComponent = (function () {
    function NavbarComponent() {
    }
    NavbarComponent.prototype.ngOnInit = function () {
        this.items = [
            {
                label: 'Dashboard',
                icon: 'fa-th-box',
                url: 'dashboard'
            },
            {
                label: 'Bundles',
                icon: 'fa-th-large',
                url: 'bundles'
            },
            {
                label: 'Services',
                icon: 'fa-play-circle',
                url: 'services'
            },
            {
                label: 'Apps',
                icon: 'fa-play-circle',
                url: 'apps'
            }
        ];
    };
    return NavbarComponent;
}());
NavbarComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'navbar',
        template: __webpack_require__("../../../../../src/app/navbar/navbar.component.html"),
        styles: [__webpack_require__("../../../../../src/app/navbar/navbar.component.css")]
    }),
    __metadata("design:paramtypes", [])
], NavbarComponent);

//# sourceMappingURL=navbar.component.js.map

/***/ }),

/***/ "../../../../../src/app/services/backend.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BackendService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_map__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var BackendService = (function () {
    function BackendService(_http /*, private _appGlobals: AppGlobalsService*/) {
        this._http = _http; /*, private _appGlobals: AppGlobalsService*/
        this.headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Headers */]();
        //this._appGlobals._config.subscribe((config) => this.config = config);
        //console.log("base url set to '" + this.config.endpoint + "'");
        //this.headers.append('Authorization', 'Basic d2ViY29uc29sZTp3ZWJjb25zb2xl');
        //this.headers.append('Access-Control-Allow-Origin', '*');
    }
    BackendService.prototype.getBundles = function () {
        return this._http.get(/*this.config.endpoint + */ '/root/bundles', { headers: this.headers })
            .map(function (res) { return res.json(); });
    };
    BackendService.prototype.getApps = function () {
        return this._http.get(/*this.config.endpoint + */ '/root/apps', { headers: this.headers })
            .map(function (res) { return res.json(); });
    };
    return BackendService;
}());
BackendService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] /*, private _appGlobals: AppGlobalsService*/ !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] /*, private _appGlobals: AppGlobalsService*/) === "function" && _a || Object])
], BackendService);

var _a;
//# sourceMappingURL=backend.service.js.map

/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
// The file contents for the current environment will overwrite these during build.
var environment = {
    production: false
};
//# sourceMappingURL=environment.js.map

/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/@angular/platform-browser-dynamic.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["enableProdMode"])();
}
Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map