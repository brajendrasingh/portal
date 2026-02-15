package com.bksoft.questionbank.dtos.i18n.dual;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class DualLanguageQuestionDTO {

    private String questionId;
    private Integer correctIndex;

    private Map<String, TranslationViewDTO> translations;
}
