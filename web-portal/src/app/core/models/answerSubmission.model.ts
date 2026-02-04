
export interface AnswerSubmission {
    questionId: number;
    questionText: string;
    answerOptions: string[];
    questionType: string;
    // selected by user
    selectedAnswers?: string[];
    correctAnswer?: string;
    userId?: string;
    assessmentId?: string;
    attemptNo?: number;
}