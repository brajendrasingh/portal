package com.bksoft.questionbank.api.models;


import com.bksoft.questionbank.dtos.QuestionCategory;
import com.bksoft.questionbank.dtos.QuestionDifficulty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Question {

    private String questionId;
    private String questionText;
    private Answer answer;
    private List<String> answerOptions;
    private Integer correctOption;     // index-based (0 or 1 based as per UI)
    private String correctAnswer;
    private String subject;
    private String topic;
    private QuestionCategory category;
    private Integer marks;
    private QuestionDifficulty difficulty;
    private String explanation;
}
