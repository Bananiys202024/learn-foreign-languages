import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainWords3Component } from './train-words3.component';

describe('TrainWords3Component', () => {
  let component: TrainWords3Component;
  let fixture: ComponentFixture<TrainWords3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainWords3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainWords3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
