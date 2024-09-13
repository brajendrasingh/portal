package com.bksoft.questionbank.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Answer {
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;	
	private String optionE;	
}
