package com.bksoft.questionbank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question_responses")
@Setter
@Getter
public class QuestionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionId;
    private String selectedAnswer;
    private Boolean correct;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private AssessmentSubmission submission;
}
