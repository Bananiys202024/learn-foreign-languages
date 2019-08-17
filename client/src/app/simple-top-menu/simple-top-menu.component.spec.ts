import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleTopMenuComponent } from './simple-top-menu.component';

describe('SimpleTopMenuComponent', () => {
  let component: SimpleTopMenuComponent;
  let fixture: ComponentFixture<SimpleTopMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimpleTopMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimpleTopMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
