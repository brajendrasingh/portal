package com.bksoft.questionbank.entities.i18n;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {
    @Id
    private String id;

    private String subject;
    private String topic;
    private String category;
    private String difficulty;
    private Integer marks;
    private Integer correctIndex;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionTranslation> translations = new ArrayList<>();
}