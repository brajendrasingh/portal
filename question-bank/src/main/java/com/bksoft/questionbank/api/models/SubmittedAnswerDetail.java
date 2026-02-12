package com.bksoft.questionbank.api.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubmittedAnswerDetail {
    public String userId;
    public String assessmentId;
    public Integer timeTakenSeconds;
    public Integer attemptNo;
    public Integer correctAnswers;
    public Integer totalQuestions;
    public LocalDateTime submittedAt;
    public List<QuestionAnswer> questionAnswers = new ArrayList<>();

    public static class QuestionAnswer {
        public String questionId;
        public String questionText;
        public List<String> answerOptions;
        public String questionType;
        public List<String> correctAnswers;
        public List<String> selectedAnswers;
    }
}
