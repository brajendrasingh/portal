import { Component } from '@angular/core';

@Component({
  selector: 'app-assessment-container',
  standalone: false,
  templateUrl: './assessment-container.component.html',
  styleUrl: './assessment-container.component.css'
})
export class AssessmentContainerComponent {
  constructor() {
    console.log('AssessmentContainerComponent LOADED');
  }
  started = false;

  filters = {
    examType: '',
    subject: '',
    questionType: '',
    difficulty: ''
  };

  onStartAssessment(filters: any) {
    console.log('AssessmentContainerComponent: Received in container:', filters);
    this.filters = filters;
    this.started = true;
  }
}
