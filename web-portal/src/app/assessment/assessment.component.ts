import { Component } from '@angular/core';

type Screen = 'SELECT' | 'INSTRUCTIONS' | 'TEST';

@Component({
  selector: 'app-assessment',
  standalone: false,
  templateUrl: './assessment.component.html',
  styleUrl: './assessment.component.css'
})
export class AssessmentComponent {

  screen: Screen = 'SELECT';
  selectedLanguage!: string;
  filters = {
    examType: '',
    examName: '',
    subject: '',
    questionType: '',
    difficulty: ''
  };
  // Step 1: selection completed
  onStartAssessment(filters: any) {
    console.log('AssessmentComponent: Received in container:', filters);
    this.filters = filters;
    this.screen = 'INSTRUCTIONS';
  }

  // Step 2: instructions accepted
  onInstructionsAccepted(language: string) {
    this.selectedLanguage = language;
    this.screen = 'TEST';
  }
}
