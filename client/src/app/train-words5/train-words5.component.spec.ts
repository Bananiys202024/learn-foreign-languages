import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainWords5Component } from './train-words5.component';

describe('TrainWords5Component', () => {
  let component: TrainWords5Component;
  let fixture: ComponentFixture<TrainWords5Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainWords5Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainWords5Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
