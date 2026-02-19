
export interface Question {
    questionId: number;
    questionText: string;
    answerOptions: string[];

    // selected by user
    selectedIndex?: number | null;
    // correct answer index
    correctOption?: number;
    correctAnswer?: string;

    // Assessment optional metadata
    subject?: string;     // e.g. "Computer Science", "Java", "Angular"
    topic?: string;    // e.g. "OOP", "DI", "Components"
    category?: string;    // e.g. "MCQ", "Descriptive"
    marks?: number;
    difficulty?: 'EASY' | 'MEDIUM' | 'HARD';
    explanation?: string; 
}