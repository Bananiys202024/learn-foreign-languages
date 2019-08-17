import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultTrainingWordsComponent } from './result-training-words.component';

describe('ResultTrainingWordsComponent', () => {
  let component: ResultTrainingWordsComponent;
  let fixture: ComponentFixture<ResultTrainingWordsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResultTrainingWordsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultTrainingWordsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
