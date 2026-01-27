import { Component, OnInit, OnDestroy } from '@angular/core';

interface Question {
  id: number;
  text: string;
  options: string[];
  correctIndex?: number;
  selectedIndex?: number;
}
@Component({
  selector: 'app-assessment',
  standalone: false,
  templateUrl: './assessment.component.html',
  styleUrl: './assessment.component.css'
})
export class AssessmentComponent implements OnInit, OnDestroy {

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
    this.questions = [
      {
        id: 1,
        text: 'What is Angular?',
        options: ['Database', 'Frontend framework', 'Backend framework', 'Operating System'],
        correctIndex: 1
      },
      {
        id: 2,
        text: 'Which directive is used for looping in Angular?',
        options: ['*ngIf', '*ngFor', '*ngSwitch', '*ngLoop'],
        correctIndex: 1
      },
      {
        id: 3,
        text: 'Which language is primarily used with Angular?',
        options: ['Java', 'Python', 'TypeScript', 'C++'],
        correctIndex: 2
      }
    ];
  }


  selectOption(question: Question, optionIndex: number): void {
    question.selectedIndex = optionIndex;
  }


  submit(): void {
    this.submitted = true;
    this.stopTimer();
    this.calculateScore();
  }

  calculateScore(): void {
    this.score = this.questions.filter(
      q => q.correctIndex !== undefined && q.selectedIndex === q.correctIndex
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

}
