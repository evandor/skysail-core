import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AppsComponent } from './apps/apps.component';
import { AppRoutingModule }     from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AppsComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
