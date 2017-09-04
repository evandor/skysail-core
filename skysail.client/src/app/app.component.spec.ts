import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component'
import { AppsComponent } from './apps/apps.component'
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule }     from './app-routing.module';
import { APP_BASE_HREF } from '@angular/common';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        AppRoutingModule
      ],
      providers: [
        {provide: APP_BASE_HREF, useValue: '/'}
      ],
      declarations: [
        AppComponent,
        AppsComponent,
        NavbarComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it(`should have as title 'app'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('app');
  }));

//  it('should render title in a h1 tag', async(() => {
//    const fixture = TestBed.createComponent(AppComponent);
//    fixture.detectChanges();
//    const compiled = fixture.debugElement.nativeElement;
//    expect(compiled.querySelector('h1').textContent).toContain('Welcome to app!');
//  }));
});
