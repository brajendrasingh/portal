package com.bksoft.questionbank.entities.i18n;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question_translations", uniqueConstraints = @UniqueConstraint(columnNames = {"question_id", "language"}))
@Getter
@Setter
public class QuestionTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String language;

    @Column(columnDefinition = "TEXT")
    private String questionText;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "translation", cascade = CascadeType.ALL)
    private List<QuestionOption> options = new ArrayList<>();
}
