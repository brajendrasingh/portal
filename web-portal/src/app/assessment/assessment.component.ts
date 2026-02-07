import { Component } from '@angular/core';

@Component({
  selector: 'app-assessment',
  standalone: false,
  templateUrl: './assessment.component.html',
  styleUrl: './assessment.component.css'
})
export class AssessmentComponent {
  constructor() {
    console.log('AssessmentComponent LOADED');
  }
  started = false;

  filters = {
    examType: '',
    examName: '',
    subject: '',
    questionType: '',
    difficulty: ''
  };

  onStartAssessment(filters: any) {
    console.log('AssessmentComponent: Received in container:', filters);
    this.filters = filters;
    this.started = true;
  }
}
