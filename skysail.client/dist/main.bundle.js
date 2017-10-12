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
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__bundle_bundle_component__ = __webpack_require__("../../../../../src/app/bundle/bundle.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__dashboard_dashboard_component__ = __webpack_require__("../../../../../src/app/dashboard/dashboard.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__generic_generic_component__ = __webpack_require__("../../../../../src/app/generic/generic.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__maincontent_maincontent_component__ = __webpack_require__("../../../../../src/app/maincontent/maincontent.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__osgi_services_osgi_services_component__ = __webpack_require__("../../../../../src/app/osgi-services/osgi-services.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__chat_component_chat_component_component__ = __webpack_require__("../../../../../src/app/chat-component/chat-component.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};










var routes = [
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: __WEBPACK_IMPORTED_MODULE_5__dashboard_dashboard_component__["a" /* DashboardComponent */] },
    { path: 'apps', component: __WEBPACK_IMPORTED_MODULE_2__apps_apps_component__["a" /* AppsComponent */] },
    { path: 'chat', component: __WEBPACK_IMPORTED_MODULE_9__chat_component_chat_component_component__["a" /* ChatComponentComponent */] },
    { path: 'bundles', component: __WEBPACK_IMPORTED_MODULE_3__bundles_bundles_component__["a" /* BundlesComponent */] },
    { path: 'services', component: __WEBPACK_IMPORTED_MODULE_8__osgi_services_osgi_services_component__["a" /* OsgiServicesComponent */] },
    {
        path: 'maincontent', component: __WEBPACK_IMPORTED_MODULE_7__maincontent_maincontent_component__["a" /* MaincontentComponent */],
        children: [
            {
                path: 'sub',
                component: __WEBPACK_IMPORTED_MODULE_2__apps_apps_component__["a" /* AppsComponent */],
            },
            {
                path: 'sub2',
                component: __WEBPACK_IMPORTED_MODULE_5__dashboard_dashboard_component__["a" /* DashboardComponent */],
            },
            {
                path: 'generic',
                component: __WEBPACK_IMPORTED_MODULE_6__generic_generic_component__["a" /* GenericComponent */],
            },
            {
                path: 'bundles',
                component: __WEBPACK_IMPORTED_MODULE_3__bundles_bundles_component__["a" /* BundlesComponent */],
            },
            {
                path: 'bundles/:id',
                component: __WEBPACK_IMPORTED_MODULE_4__bundle_bundle_component__["a" /* BundleComponent */],
            },
            {
                path: 'services',
                component: __WEBPACK_IMPORTED_MODULE_8__osgi_services_osgi_services_component__["a" /* OsgiServicesComponent */],
            },
            {
                path: '',
                redirectTo: 'sub',
                pathMatch: 'full'
            }
        ]
    },
    { path: '**', component: __WEBPACK_IMPORTED_MODULE_6__generic_generic_component__["a" /* GenericComponent */] }
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

module.exports = "<div class=\"ui-g\" style=\"height:100%;\">\n    <div class=\"ui-g-12 ui-g-nopad\">\n        <navbar></navbar>\n    </div>\n    <div class=\"ui-g-12 ui-g-nopad\">\n        <router-outlet></router-outlet>\n    </div>\n   <!-- <div class=\"ui-g-12 ui-md-2\" style=\"border-right:1px dotted gray; height:100%;\">\n        <p-panelMenu [model]=\"leftmenuitems\"></p-panelMenu>\n    </div>\n    <div class=\"ui-g-12 ui-md-10\">\n        <div class=\"ui-g-12\">\n            <p-breadcrumb [model]=\"items\"></p-breadcrumb>\n        </div>\n        <div class=\"ui-g-12 ui-g-nopad\">\n            <div class=\"ui-g\">\n              <router-outlet></router-outlet>\n            </div>\n        </div>\n    </div>\n    <div class=\"ui-g-12 ui-g-nopad\">\n        \n    </div>-->\n</div>"

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__navbar_navbar_component__ = __webpack_require__("../../../../../src/app/navbar/navbar.component.ts");
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



var AppComponent = (function () {
    function AppComponent(_backend) {
        this._backend = _backend;
        this.title = 'app';
    }
    AppComponent.prototype.ngOnInit = function () {
        /*this.items = [];
        this.items.push({ label: 'Categories' });
        this.items.push({ label: 'test', url: 'https://www.skysail.io' });
    
        this._backend.getLeftMenuItems()
        .subscribe(res => {
          this.leftmenuitems = res;
        }, error => {
          console.log("adding error to alertsService:", error);
        });*/
    };
    return AppComponent;
}());
AppComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-root',
        template: __webpack_require__("../../../../../src/app/app.component.html"),
        styles: [__webpack_require__("../../../../../src/app/app.component.css")],
        providers: [__WEBPACK_IMPORTED_MODULE_1__navbar_navbar_component__["a" /* NavbarComponent */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__services_backend_service__["a" /* BackendService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_backend_service__["a" /* BackendService */]) === "function" && _a || Object])
], AppComponent);

var _a;
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
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__services_chatService__ = __webpack_require__("../../../../../src/app/services/chatService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__bundles_bundles_component__ = __webpack_require__("../../../../../src/app/bundles/bundles.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__ = __webpack_require__("../../../../primeng/primeng.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_primeng_primeng___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_9_primeng_primeng__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__dashboard_dashboard_component__ = __webpack_require__("../../../../../src/app/dashboard/dashboard.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__generic_generic_component__ = __webpack_require__("../../../../../src/app/generic/generic.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__angular_platform_browser_animations__ = __webpack_require__("../../../platform-browser/@angular/platform-browser/animations.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__maincontent_maincontent_component__ = __webpack_require__("../../../../../src/app/maincontent/maincontent.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__leftmenu_leftmenu_component__ = __webpack_require__("../../../../../src/app/leftmenu/leftmenu.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__osgi_services_osgi_services_component__ = __webpack_require__("../../../../../src/app/osgi-services/osgi-services.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__bundle_bundle_component__ = __webpack_require__("../../../../../src/app/bundle/bundle.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__layout_infotable_infotable_component__ = __webpack_require__("../../../../../src/app/layout/infotable/infotable.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20__chat_component_chat_component_component__ = __webpack_require__("../../../../../src/app/chat-component/chat-component.component.ts");
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
            __WEBPACK_IMPORTED_MODULE_8__bundles_bundles_component__["a" /* BundlesComponent */],
            __WEBPACK_IMPORTED_MODULE_12__dashboard_dashboard_component__["a" /* DashboardComponent */],
            __WEBPACK_IMPORTED_MODULE_13__generic_generic_component__["a" /* GenericComponent */],
            __WEBPACK_IMPORTED_MODULE_15__maincontent_maincontent_component__["a" /* MaincontentComponent */],
            __WEBPACK_IMPORTED_MODULE_16__leftmenu_leftmenu_component__["a" /* LeftmenuComponent */],
            __WEBPACK_IMPORTED_MODULE_17__osgi_services_osgi_services_component__["a" /* OsgiServicesComponent */],
            __WEBPACK_IMPORTED_MODULE_18__bundle_bundle_component__["a" /* BundleComponent */],
            __WEBPACK_IMPORTED_MODULE_19__layout_infotable_infotable_component__["a" /* InfotableComponent */],
            __WEBPACK_IMPORTED_MODULE_20__chat_component_chat_component_component__["a" /* ChatComponentComponent */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["BrowserModule"],
            __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["ButtonModule"],
            //    FormsModule,
            __WEBPACK_IMPORTED_MODULE_11__angular_http__["c" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["AccordionModule"],
            __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["TabViewModule"],
            __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["MenuModule"],
            __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["MenubarModule"],
            __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["DataTableModule"], __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["SharedModule"],
            __WEBPACK_IMPORTED_MODULE_5__app_routing_module__["a" /* AppRoutingModule */],
            //    CommonModule,
            __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["PanelMenuModule"],
            __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["PanelModule"],
            __WEBPACK_IMPORTED_MODULE_9_primeng_primeng__["BreadcrumbModule"],
            __WEBPACK_IMPORTED_MODULE_5__app_routing_module__["a" /* AppRoutingModule */],
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["BrowserModule"],
            __WEBPACK_IMPORTED_MODULE_14__angular_platform_browser_animations__["a" /* BrowserAnimationsModule */],
            __WEBPACK_IMPORTED_MODULE_10__angular_forms__["FormsModule"],
            __WEBPACK_IMPORTED_MODULE_10__angular_forms__["ReactiveFormsModule"]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_6__services_backend_service__["a" /* BackendService */], __WEBPACK_IMPORTED_MODULE_7__services_chatService__["a" /* ChatService */]],
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

module.exports = "<h2><i class=\"fa fa-th-large\" aria-hidden=\"true\" style=\"color: green\"></i> Apps:</h2>\n\n<p-dataTable [value]=\"apps\" [rows]=\"20\" [paginator]=\"true\" [pageLinks]=\"5\" [rowsPerPageOptions]=\"[10,20,50,100]\">\n  <!--<ng-template ngFor let-col [ngForOf]=\"getColumns()\">\n    <p-column [field]=\"col\" [header]=\"col\"></p-column>\n  </ng-template>-->\n  <ng-template ngFor let-col [ngForOf]=\"getColumns()\">\n    <p-column [field]=\"col\" [header]=\"col\">\n      <ng-template let-col let-car=\"rowData\" let-ri=\"rowIndex\" pTemplate=\"body\">\n        <span><a href='/client{{car[\"context\"]}}'>{{car[col.field]}}</a></span>\n      </ng-template>\n    </p-column>\n  </ng-template>\n  <p-column field=\"name\" header=\"Raw\">\n    <ng-template let-col let-car=\"rowData\" let-ri=\"rowIndex\" pTemplate=\"body\">\n      <span><a href='/root{{car[\"context\"]}}/'>{{car[col.field]}}</a></span>\n    </ng-template>\n  </p-column>\n  <!--<p-column field=\"color\" header=\"Color\">\n    <ng-template let-col let-car=\"rowData\" pTemplate=\"body\">\n      <span [style.color]=\"car[col.field]\">{{car[col.field]}}</span>\n    </ng-template>\n  </p-column>\n  <p-column styleClass=\"col-button\">\n    <ng-template pTemplate=\"header\">\n      <button type=\"button\" pButton icon=\"fa-refresh\"></button>\n    </ng-template>\n    <ng-template let-car=\"rowData\" pTemplate=\"body\">\n      <button type=\"button\" pButton (click)=\"selectCar(car)\" icon=\"fa-search\"></button>\n    </ng-template>\n  </p-column>-->\n</p-dataTable>"

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

/***/ "../../../../../src/app/bundle/bundle.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/bundle/bundle.component.html":
/***/ (function(module, exports) {

module.exports = "<h4><i class=\"fa fa-th-large\" aria-hidden=\"true\" style=\"color: green\"></i> Bundle <i>{{bundle?.desc.symbolicName}}</i></h4>\n\n<p-tabView>\n  <p-tabPanel header=\"Bundle Overview\">\n    <p-panel>\n      <div class=\"ui-grid ui-grid-responsive ui-grid-pad ui-fluid\" style=\"margin: 10px 0px\">\n        <app-infotable key=\"ID\" value=\"{{bundle?.desc.id}}\"></app-infotable>\n        <app-infotable key=\"Symbolic Name\" value=\"{{bundle?.desc.symbolicName}}\"></app-infotable>\n        <app-infotable key=\"State\" value=\"{{bundle?.state}}\"></app-infotable>\n        <app-infotable key=\"Description\" value=\"{{bundle?.description}}\"></app-infotable>\n        <app-infotable key=\"Last Modification\" value=\"{{bundle?.lastModified}}\"></app-infotable>\n        <app-infotable key=\"Bundle Classpath\" value=\"{{bundle?.bundleClasspath}}\"></app-infotable>\n        <app-infotable key=\"Location\" value=\"{{bundle?.location}}\"></app-infotable>\n        <app-infotable key=\"Vendor\" value=\"{{bundle?.vendor}}\"></app-infotable>\n      </div>\n    </p-panel>\n  </p-tabPanel>\n  <p-tabPanel header=\"Exported Packages\">\n  </p-tabPanel>\n  <p-tabPanel header=\"Imported\">\n    <p-tabPanel header=\"Imported Packages\">\n    </p-tabPanel>\n  </p-tabPanel>\n  <p-tabPanel header=\"Manifest\">\n    <p-panel>\n      <div class=\"ui-grid ui-grid-responsive ui-grid-pad ui-fluid\" style=\"margin: 10px 0px\">\n        <app-infotable key=\"ID\" value=\"{{bundle?.desc.id}}\"></app-infotable>\n      </div>\n    </p-panel>\n  </p-tabPanel>\n\n</p-tabView>"

/***/ }),

/***/ "../../../../../src/app/bundle/bundle.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BundleComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_switchMap__ = __webpack_require__("../../../../rxjs/add/operator/switchMap.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_switchMap___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_switchMap__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_backend_service__ = __webpack_require__("../../../../../src/app/services/backend.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var BundleComponent = (function () {
    function BundleComponent(_backend, route, router) {
        this._backend = _backend;
        this.route = route;
        this.router = router;
    }
    BundleComponent.prototype.ngOnInit = function () {
        var _this = this;
        var id = this.route.snapshot.paramMap.get('id');
        console.log("id", id);
        this._backend.getBundleDetails(id)
            .subscribe(function (res) {
            _this.bundle = res;
        });
        /*this.bundle$ = this.route.paramMap
          .switchMap((params: ParamMap) =>
            this._backend.getBundle(params.get('id')));*/
    };
    return BundleComponent;
}());
BundleComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-bundle',
        template: __webpack_require__("../../../../../src/app/bundle/bundle.component.html"),
        styles: [__webpack_require__("../../../../../src/app/bundle/bundle.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__services_backend_service__["a" /* BackendService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_backend_service__["a" /* BackendService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["ActivatedRoute"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["ActivatedRoute"]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["Router"]) === "function" && _c || Object])
], BundleComponent);

var _a, _b, _c;
//# sourceMappingURL=bundle.component.js.map

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

module.exports = "<h4><i class=\"fa fa-th-large\" aria-hidden=\"true\" style=\"color: green\"></i> Bundles</h4>\n\n<p-tabView>\n  <p-tabPanel header=\"Bundles Overview\">\n    <!--app-errors../app-errors-->\n    <p-dataTable [value]=\"bundles\" [rows]=\"20\" selectionMode=\"single\" [(selection)]=\"selectedBundle\" (onRowSelect)=\"onRowSelect($event)\" [paginator]=\"true\" [pageLinks]=\"5\" [rowsPerPageOptions]=\"[10,20,50,100]\">\n      <p-column field=\"id\" header=\"ID\" [style]=\"{'width':'40px','font-weight': 'bold'}\">\n        <ng-template let-col let-car=\"rowData\" let-ri=\"rowIndex\" pTemplate=\"body\">\n          <span><a href='/maincontent/bundle/{{car[col.field]}}'>{{car[col.field]}}</a></span>\n        </ng-template>\n      </p-column>\n      <p-column field=\"symbolicName\" \n                header=\"Symbolic Name\" \n                [sortable]=\"true\" \n                [filter]=\"true\" filterPlaceholder=\"Search\" filterMatchMode=\"contains\" \n                [style]=\"{'width':'40%'}\">\n      </p-column>\n      <p-column field=\"providedServices\" header=\"# prov. Serv.\"></p-column>\n      <p-column field=\"usedServices\" header=\"# used Serv.\"></p-column>\n      <p-column field=\"version\" header=\"Version\" [style]=\"{'width':'180px'}\"></p-column>\n      <p-column field=\"state\" header=\"State\"></p-column>\n      <p-column field=\"size\" header=\"Size\" [sortable]=\"true\"></p-column>\n    </p-dataTable>\n  </p-tabPanel>\n  <p-tabPanel header=\"Service Dependencies\">\n    app-errors../app-errors Content 3\n  </p-tabPanel>\n  <p-tabPanel header=\"Package Dependencies\">\n    app-errors../app-errors Content 3\n  </p-tabPanel>\n</p-tabView>"

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
    BundlesComponent.prototype.onRowSelect = function (event) {
        this.router.navigate(['/maincontent/bundles/' + event.data.id]);
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

/***/ "../../../../../src/app/chat-component/chat-component.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/chat-component/chat-component.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"chat\">\n  <input [(ngModel)]=\"message\" /><button (click)=\"sendMessage()\">Click</button>\n  <div *ngFor=\"let message of messages\">\n    {{message.text}}\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/chat-component/chat-component.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ChatComponentComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_chatService__ = __webpack_require__("../../../../../src/app/services/chatService.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var ChatComponentComponent = (function () {
    function ChatComponentComponent(chatService) {
        this.chatService = chatService;
        this.messages = [];
    }
    ChatComponentComponent.prototype.sendMessage = function () {
        this.chatService.sendMessage(this.message);
        this.message = '';
    };
    ChatComponentComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.connection = this.chatService.getMessages().subscribe(function (message) {
            _this.messages.push(message);
        });
    };
    ChatComponentComponent.prototype.ngOnDestroy = function () {
        this.connection.unsubscribe();
    };
    return ChatComponentComponent;
}());
ChatComponentComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-chat-component',
        template: __webpack_require__("../../../../../src/app/chat-component/chat-component.component.html"),
        styles: [__webpack_require__("../../../../../src/app/chat-component/chat-component.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_chatService__["a" /* ChatService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_chatService__["a" /* ChatService */]) === "function" && _a || Object])
], ChatComponentComponent);

var _a;
//# sourceMappingURL=chat-component.component.js.map

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

module.exports = "<h2><i class=\"fa fa-th-large\" aria-hidden=\"true\" style=\"color: green\"></i> Dashboard:</h2>\n\n<p-dataTable [value]=\"apps\" [rows]=\"20\" [paginator]=\"true\" [pageLinks]=\"5\" [rowsPerPageOptions]=\"[10,20,50,100]\">\n  <p-column field=\"name\" header=\"Name\">\n    <ng-template let-col let-car=\"rowData\" let-ri=\"rowIndex\" pTemplate=\"body\">\n      <span><a href='/client{{car[\"context\"]}}'>{{car[col.field]}}</a></span>\n    </ng-template>\n  </p-column>\n  <p-column field=\"context\" header=\"Context\"></p-column>\n  <p-column field=\"description\" header=\"Description\"></p-column>\n</p-dataTable>\n\n<!--<p-growl [value]=\"msgs\" sticky=\"sticky\"></p-growl>-->\n\n<form [formGroup]=\"userform\" (ngSubmit)=\"onSubmit(userform.value)\">\n    <p-panel header=\"Validate\">\n        <div class=\"ui-grid ui-grid-responsive ui-grid-pad ui-fluid\" style=\"margin: 10px 0px\">\n            <div class=\"ui-grid-row\">\n                <div class=\"ui-grid-col-2\">\n                    First Name *:\n                </div>\n                <div class=\"ui-grid-col-6\">\n                    <input pInputText type=\"text\" formControlName=\"firstname\" placeholder=\"Required\"/>\n                </div>\n                <div class=\"ui-grid-col-4\">\n                    <div class=\"ui-message ui-messages-error ui-corner-all\" *ngIf=\"!userform.controls['firstname'].valid&&userform.controls['firstname'].dirty\">\n                        <i class=\"fa fa-close\"></i>\n                        Firstname is required\n                    </div>\n                </div>\n            </div>\n            <div class=\"ui-grid-row\">\n                <div class=\"ui-grid-col-2\">\n                    Last Name *:\n                </div>\n                <div class=\"ui-grid-col-6\">\n                    <input pInputText type=\"text\" formControlName=\"io.skysail.app.demo.Contact.lastname\" placeholder=\"Required\"/>\n                </div>\n                <div class=\"ui-grid-col-4\">\n                    <div class=\"ui-message ui-messages-error ui-corner-all\" *ngIf=\"!userform.controls['io.skysail.app.demo.Contact.lastname'].valid&&userform.controls['io.skysail.app.demo.Contact.lastname'].dirty\">\n                        <i class=\"fa fa-close\"></i>\n                        Lastname is required\n                    </div>\n                </div>\n            </div>\n            <div class=\"ui-grid-row\">\n                <div class=\"ui-grid-col-2\">\n                    Email *:\n                </div>\n                <div class=\"ui-grid-col-6\">\n                    <input pInputText type=\"email\" formControlName=\"email\" placeholder=\"Required - Min(6)\"/>\n                </div>\n                <div class=\"ui-grid-col-4\">\n                    <div class=\"ui-message ui-messages-error ui-corner-all\" *ngIf=\"!userform.controls['email'].valid&&userform.controls['email'].dirty\">\n                        <i class=\"fa fa-close\"></i>\n                        <span *ngIf=\"userform.controls['email'].errors['required']\">Email is required</span>\n                        <span *ngIf=\"userform.controls['email'].errors['minlength']\">Must be longer than 6 characters</span>\n                    </div>\n                </div>\n            </div>\n            <div class=\"ui-grid-row\">\n                <div class=\"ui-grid-col-2\">\n                    Description:\n                </div>\n                <div class=\"ui-grid-col-6\">\n                    <textarea pInputTextarea type=\"text\" formControlName=\"description\"></textarea>\n                </div>\n                <div class=\"ui-grid-col-4\"></div>\n            </div>\n            <!--<div class=\"ui-grid-row\">\n                <div class=\"ui-grid-col-2\">\n                    Gender *:\n                </div>\n                <div class=\"ui-grid-col-6\">\n                    <p-dropdown [options]=\"genders\" formControlName=\"gender\" [autoWidth]=\"false\"></p-dropdown>\n                </div>\n                <div class=\"ui-grid-col-4\">\n                    <div class=\"ui-message ui-messages-error ui-corner-all\" *ngIf=\"!userform.controls['gender'].valid&&userform.controls['gender'].dirty\">\n                        <i class=\"fa fa-close\"></i>\n                        Gender is required\n                    </div>\n                </div>\n            </div>-->\n            <div class=\"ui-grid-row\">\n                <div class=\"ui-grid-col-2\"></div>\n                <div class=\"ui-grid-col-6\">\n                    <button pButton type=\"submit\" label=\"Submit\" [disabled]=\"!userform.valid\"></button>\n                </div>\n                <div class=\"ui-grid-col-4\"></div>\n            </div>\n            <div style=\"text-align:center;margin-top:20px\" *ngIf=\"submitted\">\n                Form Submitted\n                <br>\n                {{diagnostic}}\n            </div>\n        </div>\n    </p-panel>\n</form>"

/***/ }),

/***/ "../../../../../src/app/dashboard/dashboard.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DashboardComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_backend_service__ = __webpack_require__("../../../../../src/app/services/backend.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



//import {Message,SelectItem} from '../../../components/common/api';
var DashboardComponent = (function () {
    function DashboardComponent(/*private router: Router, */ _backend, _fb) {
        this._backend = _backend;
        this._fb = _fb;
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
        this.userform = this._fb.group({
            'firstname': new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormControl"]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].required),
            'io.skysail.app.demo.Contact.lastname': new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormControl"]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].required),
            'email': new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormControl"]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].minLength(6)])),
            'description': new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormControl"]('') //,
            //'gender': new FormControl('', Validators.required)
        });
        /*this.genders = [];
        this.genders.push({ label: 'Select Gender', value: '' });
        this.genders.push({ label: 'Male', value: 'Male' });
        this.genders.push({ label: 'Female', value: 'Female' });*/
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
    DashboardComponent.prototype.onSubmit = function (value) {
        this.submitted = true;
        //this.msgs = [];
        // this.msgs.push({severity:'info', summary:'Success', detail:'Form Submitted'});
        var res = this._backend.postGeneric("http://localhost:8080/demo/v1/contacts/new", JSON.stringify(this.userform.value));
        console.log("RES: ", res);
        res.subscribe(function (result) { return console.log(result); }, function (error) { return console.log(error); });
    };
    Object.defineProperty(DashboardComponent.prototype, "diagnostic", {
        get: function () { return JSON.stringify(this.userform.value); },
        enumerable: true,
        configurable: true
    });
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
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormBuilder"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormBuilder"]) === "function" && _b || Object])
], DashboardComponent);

var _a, _b;
//# sourceMappingURL=dashboard.component.js.map

/***/ }),

/***/ "../../../../../src/app/domain/bundle.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Bundle; });
//import { ExportPackage } from '../domain/exportPackage';
//import { ImportPackage } from '../domain/importPackage';
//import { Service } from '../domain/service';
//import {ManifestHeader} from '../domain/manifestHeader';
//import {WireDescriptor} from '../domain/wireDescriptor';
var Bundle = (function () {
    function Bundle(id, name) {
        this.id = id;
        this.symbolicName = name;
        this.scrMap = new Map();
    }
    return Bundle;
}());

//# sourceMappingURL=bundle.js.map

/***/ }),

/***/ "../../../../../src/app/domain/bundleDetails.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BundleDetails; });
var BundleDetails = (function () {
    function BundleDetails() {
        this.state = -1;
        this.description = "";
        this.lastModified = "n/a";
        this.bundleClasspath = "n/a";
        this.location = "n/a";
        this.vendor = "n/a";
    }
    return BundleDetails;
}());

//# sourceMappingURL=bundleDetails.js.map

/***/ }),

/***/ "../../../../../src/app/domain/menuitem.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MenuItem; });
var MenuItem = (function () {
    function MenuItem(label, icon, url) {
        this.label = label;
        this.icon = icon;
        this.routerLink = url;
    }
    return MenuItem;
}());

//# sourceMappingURL=menuitem.js.map

/***/ }),

/***/ "../../../../../src/app/domain/service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Service; });
var Service = (function () {
    function Service(id, name) {
        this.id = id;
        this.objectClass = "objectClass";
    }
    return Service;
}());

//# sourceMappingURL=service.js.map

/***/ }),

/***/ "../../../../../src/app/generic/generic.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/generic/generic.component.html":
/***/ (function(module, exports) {

module.exports = "<h2><i class=\"fa fa-th-large\" aria-hidden=\"true\" style=\"color: green\"></i> Generic:</h2>\n\n<p-dataTable [value]=\"apps\" [rows]=\"20\" [paginator]=\"true\" [pageLinks]=\"5\" [rowsPerPageOptions]=\"[10,20,50,100]\">\n  <ng-template ngFor let-col [ngForOf]=\"getColumns()\">\n    <p-column [field]=\"col\" [header]=\"col\">\n      <ng-template let-col let-car=\"rowData\" let-ri=\"rowIndex\" pTemplate=\"body\">\n        <span><a href='/client{{car[\"context\"]}}'>{{car[col.field]}}</a></span>\n      </ng-template>\n    </p-column>\n  </ng-template>\n  <!--<p-column field=\"name\" header=\"Raw\">\n    <ng-template let-col let-car=\"rowData\" let-ri=\"rowIndex\" pTemplate=\"body\">\n      <span><a href='/root{{car[\"context\"]}}/'>{{car[col.field]}}</a></span>\n    </ng-template>\n  </p-column>-->\n</p-dataTable>"

/***/ }),

/***/ "../../../../../src/app/generic/generic.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GenericComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_backend_service__ = __webpack_require__("../../../../../src/app/services/backend.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
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
var GenericComponent = (function () {
    function GenericComponent(router, activatedRoute, _backend) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this._backend = _backend;
        this.path = activatedRoute.url.map(function (segments) { return segments.join('/'); });
    }
    GenericComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.path.subscribe(function (value) {
            console.log("value = '" + value + "'");
            console.log("value = '" + value.substring("/maincontent/generic".length) + "'");
            _this._backend.getGeneric("/" + value.substring("/maincontent/generic".length))
                .subscribe(function (res) {
                _this.apps = res;
            }, function (error) {
                console.log("adding error to alertsService...");
            });
        }, function (error) { return console.log(error); });
    };
    GenericComponent.prototype.getColumns = function () {
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
    GenericComponent.prototype.getField = function (col) {
        console.log("WWW", col);
        return col;
    };
    return GenericComponent;
}());
GenericComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-generic',
        template: __webpack_require__("../../../../../src/app/generic/generic.component.html"),
        styles: [__webpack_require__("../../../../../src/app/generic/generic.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["ActivatedRoute"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["ActivatedRoute"]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */]) === "function" && _c || Object])
], GenericComponent);

var _a, _b, _c;
//# sourceMappingURL=generic.component.js.map

/***/ }),

/***/ "../../../../../src/app/layout/infotable/infotable.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/layout/infotable/infotable.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"ui-grid-row\">\n  <div class=\"ui-grid-col-4\"><b>{{key}}:</b></div>\n  <div class=\"ui-grid-col-8\">{{value}}</div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/layout/infotable/infotable.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return InfotableComponent; });
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

var InfotableComponent = (function () {
    function InfotableComponent() {
    }
    InfotableComponent.prototype.ngOnInit = function () {
    };
    return InfotableComponent;
}());
InfotableComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-infotable',
        template: __webpack_require__("../../../../../src/app/layout/infotable/infotable.component.html"),
        styles: [__webpack_require__("../../../../../src/app/layout/infotable/infotable.component.css")],
        inputs: ['key', 'value']
    }),
    __metadata("design:paramtypes", [])
], InfotableComponent);

//# sourceMappingURL=infotable.component.js.map

/***/ }),

/***/ "../../../../../src/app/leftmenu/leftmenu.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/leftmenu/leftmenu.component.html":
/***/ (function(module, exports) {

module.exports = "<p-panelMenu [model]=\"leftmenuitems\"></p-panelMenu>"

/***/ }),

/***/ "../../../../../src/app/leftmenu/leftmenu.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LeftmenuComponent; });
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


var LeftmenuComponent = (function () {
    function LeftmenuComponent(_backend) {
        this._backend = _backend;
    }
    LeftmenuComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._backend.getLeftMenuItems()
            .subscribe(function (res) {
            _this.leftmenuitems = res;
        }, function (error) {
            console.log("adding error to alertsService:", error);
        });
    };
    return LeftmenuComponent;
}());
LeftmenuComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-leftmenu',
        template: __webpack_require__("../../../../../src/app/leftmenu/leftmenu.component.html"),
        styles: [__webpack_require__("../../../../../src/app/leftmenu/leftmenu.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */]) === "function" && _a || Object])
], LeftmenuComponent);

var _a;
//# sourceMappingURL=leftmenu.component.js.map

/***/ }),

/***/ "../../../../../src/app/maincontent/maincontent.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/maincontent/maincontent.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"ui-g-12 ui-md-2\" style=\"border:0px dotted gray; height:100%;\">\n  <app-leftmenu></app-leftmenu>\n  <!--<a routerLink=\"sub\" routerLinkActive=\"active\">App</a><br>\n  <a routerLink=\"sub2\" routerLinkActive=\"active\">Dash</a>-->\n</div>\n<div class=\"ui-g-12 ui-md-10\" style=\"border: 0px solid blue\">\n  <!--<div class=\"ui-g-12\">\n    <p-breadcrumb [model]=\"items\"></p-breadcrumb> breadcrumb\n  </div>-->\n  <div class=\"ui-g-12 ui-g-nopad\">\n    <div class=\"ui-g\">\n      <router-outlet></router-outlet>\n    </div>\n  </div>\n</div>\n<div class=\"ui-g-12 ui-g-nopad\" style=\"border: 0px solid green\">\n\n</div>"

/***/ }),

/***/ "../../../../../src/app/maincontent/maincontent.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MaincontentComponent; });
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

var MaincontentComponent = (function () {
    function MaincontentComponent() {
    }
    MaincontentComponent.prototype.ngOnInit = function () {
    };
    return MaincontentComponent;
}());
MaincontentComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-maincontent',
        template: __webpack_require__("../../../../../src/app/maincontent/maincontent.component.html"),
        styles: [__webpack_require__("../../../../../src/app/maincontent/maincontent.component.css")],
    }),
    __metadata("design:paramtypes", [])
], MaincontentComponent);

//# sourceMappingURL=maincontent.component.js.map

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

module.exports = "<p-menubar [model]=\"items\">\n  <!--<input type=\"text\" pInputText placeholder=\"Search\">-->\n  <button pButton icon=\"fa-sign-out\"></button>\n</p-menubar>"

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
                label: 'Home',
                icon: 'fa-home',
                url: 'dashboard'
            },
            {
                label: 'Bundles',
                icon: 'fa-th-large',
                url: 'bundles'
            },
            {
                label: 'Apps',
                icon: 'fa-play-circle',
                url: 'apps'
            },
            {
                label: 'Runtime',
                icon: 'fa-play-circle',
                url: 'maincontent'
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

/***/ "../../../../../src/app/osgi-services/osgi-services.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/osgi-services/osgi-services.component.html":
/***/ (function(module, exports) {

module.exports = "<h4><i class=\"fa fa-play-circle\" aria-hidden=\"true\" style=\"color: orange\"></i> Services:</h4>\n\n<p-tabView>\n  <p-tabPanel header=\"Services Overview\">\n    <!--app-errors../app-errors-->\n    <p-dataTable [value]=\"services\" [rows]=\"20\" [paginator]=\"true\" [pageLinks]=\"5\" [rowsPerPageOptions]=\"[10,20,50,100]\">\n      <p-column field=\"id\" header=\"ID\" [sortable]=\"true\" [style]=\"{'width':'40px','font-weight': 'bold'}\"></p-column>\n      <p-column \n        field=\"objectClass\" \n        header=\"Object Classes\"\n        [filter]=\"true\" filterPlaceholder=\"Search\" filterMatchMode=\"contains\">\n      </p-column>\n      <p-column field=\"pid\" header=\"PID\"></p-column>\n      <p-column field=\"ranking\" header=\"Ranking\" [style]=\"{'width':'180px'}\"></p-column>\n      <p-column field=\"bundleId\" header=\"Bundle ID\" [sortable]=\"true\"></p-column>\n    </p-dataTable>\n  </p-tabPanel>\n  <p-tabPanel header=\"Service Dependencies\">\n    app-errors../app-errors Content 3\n  </p-tabPanel>\n  <p-tabPanel header=\"Package Dependencies\">\n    app-errors../app-errors Content 3\n  </p-tabPanel>\n</p-tabView>"

/***/ }),

/***/ "../../../../../src/app/osgi-services/osgi-services.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return OsgiServicesComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_backend_service__ = __webpack_require__("../../../../../src/app/services/backend.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var OsgiServicesComponent = (function () {
    function OsgiServicesComponent(router, _backend) {
        this.router = router;
        this._backend = _backend;
    }
    OsgiServicesComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._backend.getServices()
            .subscribe(function (res) {
            _this.services = res;
        }, function (error) {
            console.log("adding error to alertsService...");
        });
    };
    return OsgiServicesComponent;
}());
OsgiServicesComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-osgi-services',
        template: __webpack_require__("../../../../../src/app/osgi-services/osgi-services.component.html"),
        styles: [__webpack_require__("../../../../../src/app/osgi-services/osgi-services.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_backend_service__["a" /* BackendService */]) === "function" && _b || Object])
], OsgiServicesComponent);

var _a, _b;
//# sourceMappingURL=osgi-services.component.js.map

/***/ }),

/***/ "../../../../../src/app/services/backend.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BackendService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__domain_bundle__ = __webpack_require__("../../../../../src/app/domain/bundle.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__domain_bundleDetails__ = __webpack_require__("../../../../../src/app/domain/bundleDetails.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__domain_service__ = __webpack_require__("../../../../../src/app/domain/service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__domain_menuitem__ = __webpack_require__("../../../../../src/app/domain/menuitem.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_rxjs_add_operator_catch__ = __webpack_require__("../../../../rxjs/add/operator/catch.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_rxjs_add_observable_of__ = __webpack_require__("../../../../rxjs/add/observable/of.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_rxjs_add_observable_of___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_9_rxjs_add_observable_of__);
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
        this.headerDict = {
            //'Content-Type': 'application/json',
            'Accept': 'application/json',
        };
        this.headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Headers */](this.headerDict);
        //this._appGlobals._config.subscribe((config) => this.config = config);
        //console.log("base url set to '" + this.config.endpoint + "'");
        //this.headers.append('Authorization', 'Basic d2ViY29uc29sZTp3ZWJjb25zb2xl');
        //this.headers.append('Access-Control-Allow-Origin', '*');
    }
    BackendService.prototype.getBundles = function () {
        return this._http.get('/root/bundles', { headers: this.headers })
            .map(function (res) { return res.json(); })
            .catch(function (err) {
            console.log("Backend Error in getBundles:", err);
            return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].of([new __WEBPACK_IMPORTED_MODULE_3__domain_bundle__["a" /* Bundle */]("1", "symbolicName"), new __WEBPACK_IMPORTED_MODULE_3__domain_bundle__["a" /* Bundle */]("2", "symbolicName2")]);
        });
    };
    BackendService.prototype.getBundleDetails = function (id) {
        console.log("calling '/root/bundles/'" + id);
        return this._http.get('/root/bundles/' + id, { headers: this.headers })
            .map(function (res) { return res.json(); })
            .catch(function (err) {
            console.log("Backend Error in getBundleDetails(id):", err);
            return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].of(new __WEBPACK_IMPORTED_MODULE_4__domain_bundleDetails__["a" /* BundleDetails */]());
        });
    };
    BackendService.prototype.getServices = function () {
        return this._http.get('/root/services', { headers: this.headers })
            .map(function (res) { return res.json(); })
            .catch(function (err) {
            console.log("Backend Error in getServices:", err);
            return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].of([new __WEBPACK_IMPORTED_MODULE_5__domain_service__["a" /* Service */]("1", "objectClass"), new __WEBPACK_IMPORTED_MODULE_5__domain_service__["a" /* Service */]("2", "objectClass2")]);
        });
    };
    BackendService.prototype.getApps = function () {
        return this._http.get(/*this.config.endpoint + */ '/root/apps', { headers: this.headers })
            .map(function (res) { return res.json(); });
    };
    BackendService.prototype.getLeftMenuItems = function () {
        return this._http.get('/root/apps/menus', { headers: this.headers })
            .map(function (res) { return res.json(); })
            .catch(function (err) {
            console.log("Backend Error in getLeftMenuItems:", err);
            return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].of([new __WEBPACK_IMPORTED_MODULE_6__domain_menuitem__["a" /* MenuItem */]('Bundles', 'fa-th-large', '/maincontent/bundles'), new __WEBPACK_IMPORTED_MODULE_6__domain_menuitem__["a" /* MenuItem */]('Services', 'fa-play-circle', '/maincontent/services')]);
        });
    };
    BackendService.prototype.getGeneric = function (path) {
        console.log("Backend Call to path: ", path);
        return this._http.get(path, { headers: this.headers })
            .map(function (res) { return res.json(); });
    };
    BackendService.prototype.postGeneric = function (path, payload) {
        console.log("Backend Post Call to path ", path);
        console.log("Backend Post Call with payload ", payload);
        this.headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Headers */]({
            'Content-Type': 'application/json',
            'Accept': 'q=0.8;application/json;q=0.9'
        });
        this.options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: this.headers });
        return this._http.post(path, payload, this.options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    BackendService.prototype.handleError = function (error) {
        var errMsg = (error.message) ? error.message :
            error.status ? error.status + " - " + error.statusText : 'Server error';
        console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
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

/***/ "../../../../../src/app/services/chatService.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ChatService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_socket_io_client__ = __webpack_require__("../../../../socket.io-client/lib/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_socket_io_client___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_socket_io_client__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var ChatService = (function () {
    function ChatService() {
        this.url = 'ws://localhost:8080/websocket';
    }
    ChatService.prototype.sendMessage = function (message) {
        this.socket.emit('add-message', message);
        console.log("MESSAGE SENT");
    };
    ChatService.prototype.getMessages = function () {
        var _this = this;
        var observable = new __WEBPACK_IMPORTED_MODULE_0_rxjs_Observable__["Observable"](function (observer) {
            _this.socket = __WEBPACK_IMPORTED_MODULE_2_socket_io_client__(_this.url);
            _this.socket.on('message', function (data) {
                observer.next(data);
            });
            return function () {
                _this.socket.disconnect();
            };
        });
        return observable;
    };
    return ChatService;
}());
ChatService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["Injectable"])()
], ChatService);

//# sourceMappingURL=chatService.js.map

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


/***/ }),

/***/ 1:
/***/ (function(module, exports) {

/* (ignored) */

/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map