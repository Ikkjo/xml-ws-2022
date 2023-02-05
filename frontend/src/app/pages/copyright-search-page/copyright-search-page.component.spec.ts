import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CopyrightSearchPageComponent } from './copyright-search-page.component';

describe('CopyrightSearchPageComponent', () => {
  let component: CopyrightSearchPageComponent;
  let fixture: ComponentFixture<CopyrightSearchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CopyrightSearchPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CopyrightSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
