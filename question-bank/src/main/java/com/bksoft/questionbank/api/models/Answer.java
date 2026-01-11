package com.bksoft.questionbank.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
	
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;	
	private String optionE;	
}
