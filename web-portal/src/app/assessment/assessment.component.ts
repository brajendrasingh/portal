import { Component, OnInit, OnDestroy } from '@angular/core';
import { Question } from '../core/models/question.model';
import { QuestionServiceService } from './../core/services/question-service.service';
import { AnswerSubmission } from '../core/models/answerSubmission.model';

@Component({
  selector: 'app-assessment',
  standalone: false,
  templateUrl: './assessment.component.html',
  styleUrl: './assessment.component.css'
})
export class AssessmentComponent implements OnInit, OnDestroy {

  constructor(private questionServiceService: QuestionServiceService) { }

  questions: Question[] = [];
  submitted = false;
  score = 0;

  timeLeft = 300; // seconds
  private timerId: any;

  ngOnInit(): void {
    this.loadQuestions();
    this.startTimer();
  }

  ngOnDestroy(): void {
    this.stopTimer(); // cleanup
  }

  loadQuestions(): void {
    this.questionServiceService.getQuestions().subscribe(
      (response) => {
        this.questions = response;
      }, (error) => {
        alert('Get questions failed. Please try again.');
      }
    );
  }


  selectOption(question: Question, optionIndex: number): void {
    question.selectedIndex = optionIndex;
  }


  submit(): void {
    this.submitted = true;
    this.stopTimer();
    this.calculateScore();
    const payload = this.prepareSubmissionPayload();
    console.log("Final submission payload: ", payload);
    this.submitToBackend(payload);
  }

  calculateScore(): void {
    this.score = this.questions.filter(
      q => q.correctOption !== undefined && q.selectedIndex === q.correctOption
    ).length;
  }

  startTimer(): void {
    this.timerId = setInterval(() => {
      this.timeLeft--;
      if (this.timeLeft <= 0) {
        this.submit();
      }
    }, 1000);
  }

  stopTimer(): void {
    if (this.timerId) {
      clearInterval(this.timerId);
      this.timerId = null;
    }
  }

  prepareSubmissionPayload(): AnswerSubmission[] {
    return this.questions.map(q => ({
      questionId: q.questionId,
      questionText: q.questionText,
      answerOptions: q.answerOptions,
      selectedAnswer: q.selectedIndex !== undefined ? q.answerOptions[q.selectedIndex] : null
    }));
  }

  submitToBackend(payload: AnswerSubmission[]): void {
    this.questionServiceService.submitAssessment(payload).subscribe({
      next: () => { console.log("answer submitted successfully"); },
      error: () => { console.log("answer submition failed"); }
    });
  }
}
