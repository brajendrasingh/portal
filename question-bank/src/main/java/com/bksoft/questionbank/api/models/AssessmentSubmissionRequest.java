package com.bksoft.questionbank.api.models;

import java.util.List;

public class AssessmentSubmissionRequest {

    public String userId;
    public String assessmentId;
    public Integer timeTakenSeconds;
    public Integer attemptNo;
    public String submissionStatus;
    public List<QuestionAnswer> questionAnswers;

    public static class QuestionAnswer {
        public String questionId;
        public String questionText;
        public List<String> answerOptions;
        public String questionType;
        public List<String> correctAnswers;
        public List<String> selectedAnswers;
    }
}
