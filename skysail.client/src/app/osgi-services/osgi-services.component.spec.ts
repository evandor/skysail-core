import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OsgiServicesComponent } from './osgi-services.component';

describe('OsgiServicesComponent', () => {
  let component: OsgiServicesComponent;
  let fixture: ComponentFixture<OsgiServicesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OsgiServicesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OsgiServicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
