import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadingBookComponent } from './reading-book.component';

describe('ReadingBookComponent', () => {
  let component: ReadingBookComponent;
  let fixture: ComponentFixture<ReadingBookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReadingBookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReadingBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
