import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaincontentComponent } from './maincontent.component';

describe('MaincontentComponent', () => {
  let component: MaincontentComponent;
  let fixture: ComponentFixture<MaincontentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaincontentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaincontentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});