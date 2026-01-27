
export interface Question {
    questionId: number;
    questionText: string;
    answerOptions: string[];

    // selected by user
    selectedIndex?: number;
    // correct answer index
    correctOption?: number;

    // Assessment optional metadata
    subject?: string;     // e.g. "Computer Science", "Java", "Angular"
    category?: string;    // e.g. "OOP", "DI", "Components"
    difficulty?: 'EASY' | 'MEDIUM' | 'HARD';
}