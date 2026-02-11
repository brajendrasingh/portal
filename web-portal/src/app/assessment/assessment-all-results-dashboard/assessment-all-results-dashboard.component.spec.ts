import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssessmentAllResultsDashboardComponent } from './assessment-all-results-dashboard.component';

describe('AssessmentAllResultsDashboardComponent', () => {
  let component: AssessmentAllResultsDashboardComponent;
  let fixture: ComponentFixture<AssessmentAllResultsDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssessmentAllResultsDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssessmentAllResultsDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
