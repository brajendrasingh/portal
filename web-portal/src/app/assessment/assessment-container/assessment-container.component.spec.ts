import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssessmentContainerComponent } from './assessment-container.component';

describe('AssessmentContainerComponent', () => {
  let component: AssessmentContainerComponent;
  let fixture: ComponentFixture<AssessmentContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssessmentContainerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssessmentContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
