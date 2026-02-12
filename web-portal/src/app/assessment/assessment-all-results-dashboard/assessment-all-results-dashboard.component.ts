import { Component } from '@angular/core';
import { QuestionServiceService } from '../../core/services/question-service.service';

@Component({
  selector: 'app-assessment-results-dashboard',
  standalone: false,
  templateUrl: './assessment-all-results-dashboard.component.html',
  styleUrl: './assessment-all-results-dashboard.component.css'
})
export class AssessmentAllResultsDashboardComponent {

  constructor(private questionService: QuestionServiceService) { }

  results: any[] = [];

  ngOnInit(): void {
    this.loadResultsDashboard();
  }

  loadResultsDashboard(): void {
    this.questionService.getAllResults().subscribe({
      next: (response: any[]) => {
        this.results = response || [];
      },
      error: () => {
        alert('Failed to load assessment results. Please try again.');
      }
    });
  }
}

