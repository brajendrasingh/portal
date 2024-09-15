package com.bksoft.questionbank.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Getter
public class QuestionEntity {

	@Id
	private String questionId;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;	
	private String optionE;	
	private String correctOption;
}
