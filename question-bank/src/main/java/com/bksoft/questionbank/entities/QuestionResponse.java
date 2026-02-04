package com.bksoft.questionbank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "question_responses")
@Setter
@Getter
public class QuestionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionId;
    private String questionType;
    private List<String> selectedAnswers;
    private Boolean correct;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private AssessmentSubmission submission;
}
