package com.bksoft.questionbank.dtos.i18n;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuestionResponseDTO {

    private String id;
    private String questionText;
    private List<String> options;
    private Integer correctIndex;
    private String explanation;
}
