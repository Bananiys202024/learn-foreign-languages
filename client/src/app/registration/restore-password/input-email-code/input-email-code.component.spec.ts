import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputEmailCodeComponent } from './input-email-code.component';

describe('InputEmailCodeComponent', () => {
  let component: InputEmailCodeComponent;
  let fixture: ComponentFixture<InputEmailCodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputEmailCodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputEmailCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
