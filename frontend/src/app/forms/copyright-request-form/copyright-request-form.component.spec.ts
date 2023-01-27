import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CopyrightRequestFormComponent } from './copyright-request-form.component';

describe('CopyrightRequestFormComponent', () => {
  let component: CopyrightRequestFormComponent;
  let fixture: ComponentFixture<CopyrightRequestFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CopyrightRequestFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CopyrightRequestFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
