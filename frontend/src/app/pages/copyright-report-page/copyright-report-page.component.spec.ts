import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CopyrightReportPageComponent } from './copyright-report-page.component';

describe('CopyrightReportPageComponent', () => {
  let component: CopyrightReportPageComponent;
  let fixture: ComponentFixture<CopyrightReportPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CopyrightReportPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CopyrightReportPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
