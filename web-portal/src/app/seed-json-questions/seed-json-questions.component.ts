import { Component } from '@angular/core';
import { QuestionServiceService } from '../../app/core/services/question-service.service';

@Component({
  selector: 'app-seed-json-questions',
  standalone: false,
  templateUrl: './seed-json-questions.component.html',
  styleUrl: './seed-json-questions.component.css'
})
export class SeedJsonQuestionsComponent {
  jsonInput: string = '';
  loading: boolean = false;
  message: string = '';
  error: string = '';

  constructor(private questionService: QuestionServiceService) { }

  submitJson() {
    this.message = '';
    this.error = '';
    try {
      const parsedPayload = JSON.parse(this.jsonInput);
      this.loading = true;
      this.questionService.uploadQuestions(parsedPayload).subscribe({
        next: (res) => {
          this.message = '✅ Questions uploaded successfully!';
          this.jsonInput = '';
          this.loading = false;
        },
        error: (err) => {
          this.error = '❌ Failed to upload questions';
          console.error(err);
          this.loading = false;
        }
      });
    } catch (e) {
      this.error = '❌ Invalid JSON format';
    }
  }
  formatJson() {
    try {
      const parsed = JSON.parse(this.jsonInput);
      this.jsonInput = JSON.stringify(parsed, null, 2); // pretty format
    } catch (e) {
      this.error = '❌ Invalid JSON, cannot format';
    }
  }
  clearJson() {
    this.jsonInput = '';
    this.message = '';
    this.error = '';
  }
}
