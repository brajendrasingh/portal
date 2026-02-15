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
    { code: 'en', label: 'English' },
    { code: 'hi', label: 'Hindi' },
    { code: 'en-hi', label: 'English+Hindi' },
  ];

  ngOnInit() {
    this.setBrowserLanguage();
  }

  setBrowserLanguage() {
    const browserLang = navigator.language || (navigator as any).userLanguage;
    // browserLang example: "en-US", "hi-IN"
    const langCode = browserLang.split('-')[0].toUpperCase();
    const match = this.languages.find(l => l.code === langCode);
    this.selectedLanguage = match ? match.code : 'EN'; // fallback to English
  }

  startTestClick() {
    //Store language in localStorage
    localStorage.setItem('lang', this.selectedLanguage);
    this.startTest.emit(this.selectedLanguage);
  }
}
