import { Component, EventEmitter, Output } from '@angular/core';


@Component({
  selector: 'app-pre-assessment-subject-selection',
  standalone: false,
  templateUrl: './pre-assessment-subject-selection.component.html',
  styleUrl: './pre-assessment-subject-selection.component.css'
})
export class PreAssessmentSubjectSelectionComponent {

  @Output() filterChange = new EventEmitter<{
    examType: string;
    examName: string;
    subject: string;
    questionType: string;
    difficulty: string;
  }>();

  @Output() startAssessment = new EventEmitter<{
    examType: string;
    examName: string;
    subject: string;
    questionType: string;
    difficulty: string;
  }>();

  filter = {
    examType: '',
    examName: '',
    subject: '',
    questionType: '',
    difficulty: ''
  };

  canSubmit = false;
  isGovtExam = false;

  onChange() {
    this.isGovtExam = this.filter.examType === 'Govt';
    if (this.isGovtExam) {
      this.filter.subject = ''
      this.filter.questionType = ''
      this.filter.difficulty = ''
    } else {
      this.filter.examName = '';
    }
    this.canSubmit = this.isGovtExam ? !!this.filter.examName : !!(this.filter.subject && this.filter.questionType && this.filter.difficulty);
    this.filterChange.emit({ ...this.filter });
  }

  startTest() {
    console.log('PreAssessmentSubjectSelectionComponent: Start Test clicked', this.filter);
    this.startAssessment.emit({ ...this.filter });
  }
}
