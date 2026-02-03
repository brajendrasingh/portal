package com.bksoft.questionbank.api.models;

import java.util.List;

public class AssessmentSubmissionRequest {

    public Long userId;
    public Long assessmentId;
    public Integer timeTakenSeconds;
    public List<QuestionAnswer> answers;

    public static class QuestionAnswer {
        public Long questionId;
        public String selectedAnswer;
    }
}
