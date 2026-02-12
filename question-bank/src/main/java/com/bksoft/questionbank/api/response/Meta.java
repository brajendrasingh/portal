package com.bksoft.questionbank.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private Integer totalSubjects;
    private Integer totalQuestions;
    private Integer page;
    private Integer pageSize;
}
