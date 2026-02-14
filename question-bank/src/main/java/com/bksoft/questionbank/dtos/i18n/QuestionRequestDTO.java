package com.bksoft.questionbank.dtos.i18n;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionRequestDTO {

    private String id;
    private String subject;
    private String topic;
    private String category;
    private String difficulty;
    private Integer marks;
    private Integer correctIndex;

    private List<TranslationDTO> translations;
}
