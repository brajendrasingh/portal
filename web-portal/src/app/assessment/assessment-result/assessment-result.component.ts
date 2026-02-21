import { Component, Input } from '@angular/core';
import { AnswerSubmission } from '../../core/models/answerSubmission.model';
import { QuestionServiceService } from '../../core/services/question-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-result',
  standalone: false,
  templateUrl: './assessment-result.component.html',
  styleUrl: './assessment-result.component.css'
})
export class AssessmentResultComponent {
  userId!: string;
  assessmentId!: string
  attemptNo!: number

  // Result data
  questions: AnswerSubmission[] = [];
  // Pagination properties
  currentPage: number = 0;
  pageSize: number = 2;
  pageSizeOptions: number[] = [1, 2, 5, 10, 20, 50, 100];

  // Summary values
  @Input() totalQuestions = 0;
  @Input() correctAnswers = 0;
  @Input() passingPercentage = 60;

  constructor(private route: ActivatedRoute, private questionService: QuestionServiceService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('userId')!;
    this.assessmentId = this.route.snapshot.paramMap.get('assessmentId')!;
    this.attemptNo = Number(this.route.snapshot.paramMap.get('attemptNo'));
    this.loadResults();
  }

  loadResults(): void {
    this.questionService.getResultByAssessmentId(this.userId, this.assessmentId, this.attemptNo).subscribe({
      next: (response: any) => {
        this.questions = response.questionAnswers;
        // Calculate summary values
        this.totalQuestions = response.totalQuestions;
        this.correctAnswers = response.correctAnswers;
      },
      error: () => {
        alert('Failed to load assessment results. Please try again.');
      }
    });
  }

  get scorePercentage(): number {
    if (this.totalQuestions === 0) {
      return 0;
    }
    return Math.round((this.correctAnswers / this.totalQuestions) * 100);
  }

  get resultStatus(): string {
    return this.scorePercentage >= this.passingPercentage ? 'PASS' : 'FAIL';
  }

  isCorrect(q: AnswerSubmission): boolean {
    if (!q.selectedAnswers || !q.correctAnswers) {
      return false;
    }
    return JSON.stringify(q.selectedAnswers.sort()) === JSON.stringify(q.correctAnswers.sort());
  }

  // Total pages
  get totalPages(): number {
    return Math.ceil((this.questions?.length || 0) / this.pageSize);
  }

  // Paginated questions
  get paginatedQuestions(): AnswerSubmission[] {
    const start = this.currentPage * this.pageSize;
    const end = start + this.pageSize;
    return this.questions.slice(start, end);
  }

  // Change page
  changePage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;

      // Optional: scroll to top smoothly
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }
  }

  // Change page size
  changePageSize(size: number): void {
    this.pageSize = size;
    this.currentPage = 0;
  }
}