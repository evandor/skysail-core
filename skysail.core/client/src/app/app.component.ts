import { Component } from '@angular/core';
import { NavbarComponent } from './navbar/navbar.component'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [NavbarComponent]
})
export class AppComponent {
  title = 'app';
}
