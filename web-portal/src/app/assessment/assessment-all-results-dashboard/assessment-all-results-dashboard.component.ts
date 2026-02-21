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
  currentPage: number = 0;
  pageSize: number = 10;
  pageSizeOptions: number[] = [5, 10, 20, 30, 40, 50, 100];
  totalPages: number = 0;
  totalElements: number = 0;

  ngOnInit(): void {
    this.loadResultsDashboard();
  }

  // loadResultsDashboard(): void {
  //   this.questionService.getAllResults().subscribe({
  //     next: (response: any[]) => {
  //       this.results = response || [];
  //     },
  //     error: () => {
  //       alert('Failed to load assessment results. Please try again.');
  //     }
  //   });
  // }

  loadResultsDashboard(): void {
    this.questionService.getResults(this.currentPage, this.pageSize).subscribe({
      next: (response: any) => {
        this.results = response.content;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
      },
      error: () => {
        alert('Failed to load assessment results. Please try again.');
      }
    });
  }

  changePage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
      this.loadResultsDashboard();
    }
  }

  changePageSize(size: number): void {
    this.pageSize = size;
    this.currentPage = 0; // reset to first page
    this.loadResultsDashboard();
  }
}

