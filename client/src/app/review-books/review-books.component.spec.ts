import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewBooksComponent } from './review-books.component';

describe('ReviewBooksComponent', () => {
  let component: ReviewBooksComponent;
  let fixture: ComponentFixture<ReviewBooksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewBooksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
