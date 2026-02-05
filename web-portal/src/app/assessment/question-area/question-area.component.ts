import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Question } from '../../core/models/question.model';
import { QuestionServiceService } from './../../core/services/question-service.service';
import { AnswerSubmission } from '../../core/models/answerSubmission.model';
import { AssessmentSubmissionPayload } from '../../core/models/assessmentSubmissionPayload.model'

interface Section {
  sectionName: string;   // Math / Science
  questions: Question[];
}

interface AssessmentResponse {
  data: {
    [sectionName: string]: Question[];
  };
}

@Component({
  selector: 'app-question-area',
  standalone: false,
  templateUrl: './question-area.component.html',
  styleUrl: './question-area.component.css'
})

export class QuestionAreaComponent implements OnInit, OnDestroy {

  constructor(private questionServiceService: QuestionServiceService) { }

  /* ================= HEADER ================= */
  assessmentName = 'Science & Math Assessment';

  duration = 15 * 60;
  timeLeft = this.duration;
  private timer!: ReturnType<typeof setInterval>;
  submitted = false;

  /* ================= DATA ================= */
  sections: Section[] = [];

  /* ================= STATE ================= */
  currentSectionIndex = 0;
  currentQuestionIndex = 0;

  /* ================= LIFECYCLE ================= */
  ngOnInit(): void {
    this.loadAssessment();
    this.startTimer();
  }

  ngOnDestroy(): void {
    clearInterval(this.timer);
  }

  /* ================= LOAD JSON ================= */
  loadAssessment(): void {
    this.questionServiceService.getV1Questions().subscribe({
      next: (response: AssessmentResponse) => {
        this.sections = Object.keys(response.data).map(sectionName => ({
          sectionName,
          questions: response.data[sectionName].map(q => ({
            ...q,
            selectedIndex: null   // ensure UI field exists
          }))
        }));
        // reset navigation
        this.currentSectionIndex = 0;
        this.currentQuestionIndex = 0;
      },
      error: () => {
        alert('Get questions failed. Please try again.');
      }
    });
  }
  /* ================= GETTERS ================= */

  get currentSection(): Section {
    return this.sections[this.currentSectionIndex];
  }

  get currentQuestion(): Question {
    return this.currentSection.questions[this.currentQuestionIndex];
  }

  get totalQuestions(): number {
    return this.sections.reduce((sum, sec) => sum + sec.questions.length, 0);
  }

  get attemptedQuestions(): number {
    return this.sections.reduce(
      (sum, sec) =>
        sum + sec.questions.filter(q => q.selectedIndex !== null).length,
      0
    );
  }

  /* ================= NAVIGATION ================= */

  switchSection(index: number): void {
    this.currentSectionIndex = index;
    this.currentQuestionIndex = 0;
  }

  goToQuestion(index: number): void {
    this.currentQuestionIndex = index;
  }

  nextQuestion(): void {
    if (this.currentQuestionIndex < this.currentSection.questions.length - 1) {
      this.currentQuestionIndex++;
    } else {
      this.nextSection();
    }
  }

  nextSection(): void {
    if (this.currentSectionIndex < this.sections.length - 1) {
      this.currentSectionIndex++;
      this.currentQuestionIndex = 0;
    }
  }

  /* ================= ANSWER ================= */

  selectOption(index: number): void {
    this.currentQuestion.selectedIndex = index;
  }

  hasAnyAnswer(): boolean {
    return this.sections.some(sec =>
      sec.questions.some(q => q.selectedIndex !== null)
    );
  }

  /* ================= TIMER ================= */

  startTimer(): void {
    this.timer = setInterval(() => {
      this.timeLeft--;

      if (this.timeLeft <= 0) {
        this.timeLeft = 0;
        this.submit();
      }
    }, 1000);
  }

  formatTime(): string {
    const min = Math.floor(this.timeLeft / 60);
    const sec = this.timeLeft % 60;
    return `${min}:${sec < 10 ? '0' : ''}${sec}`;
  }

  /* ================= SUBMIT ================= */

  submit(): void {
    if (this.submitted) return;

    this.submitted = true;
    clearInterval(this.timer);

    console.log('Submitted Payload:', this.sections);
    alert('Assessment submitted successfully!');

    const payload = this.prepareSubmissionPayload();
    console.log("Final submission payload: ", payload);
    this.submitToBackend(payload);
  }

  /* ================= For SUBMIT payload================= */

  prepareSubmissionPayload(): AssessmentSubmissionPayload {
    const questionAnswers: AnswerSubmission[] = this.sections
      .flatMap(section =>
        section.questions.map(q => ({
          questionId: q.questionId,
          questionText: q.questionText,
          answerOptions: q.answerOptions,
          // if MCQ single select
          selectedAnswers: q.selectedIndex != null ? [q.answerOptions[q.selectedIndex]] : [],
          questionType: 'MCQ',
          subject: section.sectionName
        }))
      );
    return {
      userId: 'anonymous',
      assessmentId: '123458',
      attemptNo: 1,
      timeTakenSeconds: this.duration - this.timeLeft,
      submissionStatus: 'submitted',
      questionAnswers
    };
  }

  submitToBackend(payload: AssessmentSubmissionPayload): void {
    this.questionServiceService.submitAssessment(payload).subscribe({
      next: () => { console.log("answer submitted successfully"); },
      error: () => { console.log("answer submition failed"); }
    });
  }
}

