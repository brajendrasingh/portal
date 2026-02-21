import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-submission-confirmation',
  standalone: false,
  templateUrl: './submission-confirmation.component.html',
  styleUrl: './submission-confirmation.component.css'
})
export class SubmissionConfirmationComponent {
  userId!: string;
  assessmentId!: string;
  attemptNo!: number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('userId')!;
    this.assessmentId = this.route.snapshot.paramMap.get('assessmentId')!;
    this.attemptNo = Number(this.route.snapshot.paramMap.get('attemptNo'));
  }
}
