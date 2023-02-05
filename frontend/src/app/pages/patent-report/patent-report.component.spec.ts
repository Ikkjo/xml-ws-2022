import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentReportComponent } from './patent-report.component';

describe('PatentReportComponent', () => {
  let component: PatentReportComponent;
  let fixture: ComponentFixture<PatentReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatentReportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatentReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
