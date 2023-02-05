import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentSearchComponent } from './patent-search.component';

describe('PatentSearchComponent', () => {
  let component: PatentSearchComponent;
  let fixture: ComponentFixture<PatentSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatentSearchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatentSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
