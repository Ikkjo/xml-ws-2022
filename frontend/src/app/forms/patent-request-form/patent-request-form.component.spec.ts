import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentRequestFormComponent } from './patent-request-form.component';

describe('PatentRequestFormComponent', () => {
  let component: PatentRequestFormComponent;
  let fixture: ComponentFixture<PatentRequestFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatentRequestFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatentRequestFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
