import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AppsComponent } from './apps/apps.component';
import { AppRoutingModule }     from './app-routing.module';
import { BackendService } from './services/backend.service';
import { BundlesComponent } from './bundles/bundles.component';

import { AccordionModule, TabViewModule, MenuModule, MenubarModule,DataTableModule,SharedModule } from 'primeng/primeng';
import { HttpModule } from '@angular/http';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AppsComponent,
    BundlesComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
//    FormsModule,
    HttpModule,
    AccordionModule,
    TabViewModule,
    MenuModule,
    MenubarModule,
    DataTableModule,SharedModule,
    AppRoutingModule,
//    CommonModule,
    AppRoutingModule,
    BrowserModule
  ],
  providers: [BackendService],
  bootstrap: [AppComponent]
})
export class AppModule { }
