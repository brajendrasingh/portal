import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreAssessmentSubjectSelectionComponent } from './pre-assessment-subject-selection.component';

describe('PreAssessmentSubjectSelectionComponent', () => {
  let component: PreAssessmentSubjectSelectionComponent;
  let fixture: ComponentFixture<PreAssessmentSubjectSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PreAssessmentSubjectSelectionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PreAssessmentSubjectSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
