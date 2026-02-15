package com.bksoft.questionbank.dtos.i18n.dual;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TranslationViewDTO {

    private String questionText;
    private List<String> answerOptions;
    private String explanation;
}
