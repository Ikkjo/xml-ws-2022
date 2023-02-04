import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegsitrationPageComponent } from './regsitration-page.component';

describe('RegsitrationPageComponent', () => {
  let component: RegsitrationPageComponent;
  let fixture: ComponentFixture<RegsitrationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegsitrationPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegsitrationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
