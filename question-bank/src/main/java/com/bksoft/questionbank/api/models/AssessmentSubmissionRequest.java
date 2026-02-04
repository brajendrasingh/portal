package com.bksoft.questionbank.api.models;

import java.util.List;

public class AssessmentSubmissionRequest {

    public Long userId;
    public Long assessmentId;
    public Integer timeTakenSeconds;
    public Integer attemptNo;
    public String submissionStatus;
    public List<QuestionAnswer> questionAnswers;

    public static class QuestionAnswer {
        public String questionId;
        private String questionText;
        private List<String> answerOptions;
        public String questionType;
        public List<String> correctAnswers;
        public List<String> selectedAnswers;
    }
}
