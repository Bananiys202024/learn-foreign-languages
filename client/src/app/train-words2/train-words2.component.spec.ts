import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainWords2Component } from './train-words2.component';

describe('TrainWords2Component', () => {
  let component: TrainWords2Component;
  let fixture: ComponentFixture<TrainWords2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainWords2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainWords2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
