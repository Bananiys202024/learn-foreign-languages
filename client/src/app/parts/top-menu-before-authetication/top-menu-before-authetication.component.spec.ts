import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopMenuBeforeAutheticationComponent } from './top-menu-before-authetication.component';

describe('TopMenuBeforeAutheticationComponent', () => {
  let component: TopMenuBeforeAutheticationComponent;
  let fixture: ComponentFixture<TopMenuBeforeAutheticationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopMenuBeforeAutheticationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopMenuBeforeAutheticationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
