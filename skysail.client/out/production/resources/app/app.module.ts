import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AppsComponent } from './apps/apps.component';
import { AppRoutingModule } from './app-routing.module';
import { BackendService } from './services/backend.service';
import { ChatService } from './services/chatService';
import { BundlesComponent } from './bundles/bundles.component';

import {
  AccordionModule, ButtonModule, TabViewModule, MenuModule, MenubarModule,
  DataTableModule, SharedModule, BreadcrumbModule, PanelMenuModule, PanelModule
} from 'primeng/primeng';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpModule } from '@angular/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import { GenericComponent } from './generic/generic.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaincontentComponent } from './maincontent/maincontent.component';
import { LeftmenuComponent } from './leftmenu/leftmenu.component';
import { OsgiServicesComponent } from './osgi-services/osgi-services.component';
import { BundleComponent } from './bundle/bundle.component';
import { InfotableComponent } from './layout/infotable/infotable.component';
import { ChatComponentComponent } from './chat-component/chat-component.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AppsComponent,
    BundlesComponent,
    DashboardComponent,
    GenericComponent,
    MaincontentComponent,
    LeftmenuComponent,
    OsgiServicesComponent,
    BundleComponent,
    InfotableComponent,
    ChatComponentComponent
  ],
  imports: [
    BrowserModule,
    ButtonModule,
    //    FormsModule,
    HttpModule,
    AccordionModule,
    TabViewModule,
    MenuModule,
    MenubarModule,
    DataTableModule, SharedModule,
    AppRoutingModule,
    //    CommonModule,
    PanelMenuModule,
    PanelModule,
    BreadcrumbModule,
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [BackendService,ChatService],
  bootstrap: [AppComponent]
})
export class AppModule { }
