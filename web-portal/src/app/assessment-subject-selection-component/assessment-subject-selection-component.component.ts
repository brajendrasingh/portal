import { Component, EventEmitter, Output } from '@angular/core';


@Component({
  selector: 'app-assessment-subject-selection-component',
  standalone: false,
  templateUrl: './assessment-subject-selection-component.component.html',
  styleUrl: './assessment-subject-selection-component.component.css'
})
export class AssessmentSubjectSelectionComponentComponent {

  @Output() filterChange = new EventEmitter<{
    examType: string;
    subject: string;
    questionType: string;
    difficulty: string;
  }>();

  @Output() startAssessment = new EventEmitter<{
    examType: string;
    subject: string;
    questionType: string;
    difficulty: string;
  }>();

  filter = {
    examType: '',
    subject: '',
    questionType: '',
    difficulty: ''
  };
  canSubmit = false;
  isGovtExam = false;
  onChange() {
    this.isGovtExam = this.filter.examType === 'Govt';
    if (this.isGovtExam) {
      this.filter.subject = 'Math'
      this.filter.questionType = 'MCQ'
      this.filter.difficulty = 'NA'
      this.canSubmit = true;
    }
    else {
      this.canSubmit = !!(
        this.filter.subject &&
        this.filter.questionType &&
        this.filter.difficulty
      );
    }
    this.filterChange.emit({ ...this.filter });
  }

  startTest() {
    console.log('AssessmentSubjectSelectionComponentComponent: Start Test clicked', this.filter);
    this.startAssessment.emit({ ...this.filter });
  }
}
