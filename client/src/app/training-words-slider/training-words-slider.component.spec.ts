import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingWordsSliderComponent } from './training-words-slider.component';

describe('TrainingWordsSliderComponent', () => {
  let component: TrainingWordsSliderComponent;
  let fixture: ComponentFixture<TrainingWordsSliderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingWordsSliderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingWordsSliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
