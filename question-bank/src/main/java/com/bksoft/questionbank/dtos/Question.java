package com.bksoft.questionbank.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Question {
	
	private String questionId;
	private String question;
	private Answer answer;
	private String correctOption;
}
