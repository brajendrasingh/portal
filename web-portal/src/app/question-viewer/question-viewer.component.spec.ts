import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionViewerComponent } from './question-viewer.component';

describe('QuestionViewerComponent', () => {
  let component: QuestionViewerComponent;
  let fixture: ComponentFixture<QuestionViewerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuestionViewerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
