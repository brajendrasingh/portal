package com.bksoft.questionbank.entities.i18n;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question_options")
@Getter
@Setter
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer optionIndex;

    @Column(columnDefinition = "TEXT")
    private String optionText;

    @ManyToOne
    @JoinColumn(name = "translation_id")
    private QuestionTranslation translation;
}
