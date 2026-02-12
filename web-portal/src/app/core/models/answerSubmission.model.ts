
export interface AnswerSubmission {
    questionId: number;
    questionText: string;
    answerOptions: string[];
    questionType: string;
    // selected by user
    selectedAnswers?: string[];
    correctAnswers?: string[];
    userId?: string;
    assessmentId?: string;
    attemptNo?: number;
}