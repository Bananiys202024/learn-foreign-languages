import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TVComponent } from './tv.component';

describe('TVComponent', () => {
  let component: TVComponent;
  let fixture: ComponentFixture<TVComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TVComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TVComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
