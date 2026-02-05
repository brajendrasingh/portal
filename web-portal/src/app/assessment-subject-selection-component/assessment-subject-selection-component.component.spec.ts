import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssessmentSubjectSelectionComponentComponent } from './assessment-subject-selection-component.component';

describe('AssessmentSubjectSelectionComponentComponent', () => {
  let component: AssessmentSubjectSelectionComponentComponent;
  let fixture: ComponentFixture<AssessmentSubjectSelectionComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssessmentSubjectSelectionComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssessmentSubjectSelectionComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
