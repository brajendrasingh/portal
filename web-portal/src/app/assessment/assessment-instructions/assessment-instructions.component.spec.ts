import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssessmentInstructionsComponent } from './assessment-instructions.component';

describe('AssessmentInstructionsComponent', () => {
  let component: AssessmentInstructionsComponent;
  let fixture: ComponentFixture<AssessmentInstructionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssessmentInstructionsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssessmentInstructionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
