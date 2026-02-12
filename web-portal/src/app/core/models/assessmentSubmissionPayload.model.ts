import { AnswerSubmission } from './answerSubmission.model';

export interface AssessmentSubmissionPayload {
    userId?: string;
    assessmentId?: string;
    attemptNo?: number;
    timeTakenSeconds: number;
    submissionStatus: 'submitted' | 'draft';
    questionAnswers: AnswerSubmission[];
}