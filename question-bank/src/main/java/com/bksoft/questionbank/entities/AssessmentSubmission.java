package com.bksoft.questionbank.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assessment_submissions")
@Setter
@Getter
public class AssessmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long assessmentId;
    public Integer attemptNo;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Integer score;
    private Boolean passed;
    private Integer timeTakenSeconds;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String evaluatedBy;
    private LocalDateTime submittedAt;
    private LocalDateTime evaluatedAt;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    @JsonManagedReference //For fixing exception of nested json
    private List<QuestionResponse> responses;

    public enum Status {
        SUBMITTED, EVALUATED
    }
}
