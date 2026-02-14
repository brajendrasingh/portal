package com.bksoft.questionbank.dtos.i18n;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TranslationDTO {

    private String language;
    private String questionText;
    private String explanation;
    private List<String> options;
}
