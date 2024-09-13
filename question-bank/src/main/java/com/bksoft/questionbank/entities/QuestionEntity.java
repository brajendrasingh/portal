package com.bksoft.questionbank.entities;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestionEntity {

	private String questionId;
	private String question;
	private Answer answer;
	private String correctOption;
}
