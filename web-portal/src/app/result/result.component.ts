import { Component, Input } from '@angular/core';
import { AnswerSubmission } from '../core/models/answerSubmission.model';
import { QuestionServiceService } from './../core/services/question-service.service';

@Component({
  selector: 'app-result',
  standalone: false,
  templateUrl: './result.component.html',
  styleUrl: './result.component.css'
})
export class ResultComponent {

  // Result data
  questions: AnswerSubmission[] = [];

  // Summary values
  @Input() totalQuestions = 0;
  @Input() correctAnswers = 0;
  @Input() passingPercentage = 60;

  constructor(private questionService: QuestionServiceService) { }

  ngOnInit(): void {
    this.loadResults();
  }

  loadResults(): void {
    this.questionService.getResults('').subscribe({
      next: (response: AnswerSubmission[]) => {
        this.questions = response || [];

        // Calculate summary values
        this.totalQuestions = this.questions.length;
        this.correctAnswers = this.questions.filter(q => q.selectedAnswer === q.correctAnswer).length;
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
}