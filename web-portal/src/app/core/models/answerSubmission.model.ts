
export interface AnswerSubmission {
    questionId: number;
    questionText: string;
    answerOptions: string[];

    // selected by user
    selectedAnswer?: string | null;
    correctAnswer?: string;
    userId?: string;
    assessmentId?: string;
    attemptNo?: number;
}