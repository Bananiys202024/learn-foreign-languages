import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainWords4Component } from './train-words4.component';

describe('TrainWords4Component', () => {
  let component: TrainWords4Component;
  let fixture: ComponentFixture<TrainWords4Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainWords4Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainWords4Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
