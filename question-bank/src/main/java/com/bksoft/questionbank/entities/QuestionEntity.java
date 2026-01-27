package com.bksoft.questionbank.entities;

import com.bksoft.questionbank.dtos.QuestionCategory;
import com.bksoft.questionbank.dtos.QuestionDifficulty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "questions")
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
	private String correctAnswer;
	private List<String> answerOptions;
	private String subject;
	private String topic;
	private QuestionCategory category;
	private Integer marks;
	private QuestionDifficulty difficulty;
	private String explanation;
}
