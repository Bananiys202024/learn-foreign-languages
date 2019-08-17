import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainWords1Component } from './train-words1.component';

describe('TrainWords1Component', () => {
  let component: TrainWords1Component;
  let fixture: ComponentFixture<TrainWords1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainWords1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainWords1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
