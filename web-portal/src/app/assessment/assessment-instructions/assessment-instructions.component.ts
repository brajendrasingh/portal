import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-assessment-instructions',
  standalone: false,
  templateUrl: './assessment-instructions.component.html',
  styleUrl: './assessment-instructions.component.css'
})
export class AssessmentInstructionsComponent {
  @Input() config!: any;
  @Output() startTest = new EventEmitter<string>();

  accepted = false;
  selectedLanguage!: string;
  languages = [
    { code: 'EN', label: 'English' },
    { code: 'HI', label: 'Hindi' }
  ];

  startTestClick() {
    this.startTest.emit(this.selectedLanguage);
  }
}
