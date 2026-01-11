package com.bksoft.questionbank.api.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
	
	private String questionId;
	private String question;
	private Answer answer;
}
