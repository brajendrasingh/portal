package com.bksoft.questionbank.repositories;

import org.springframework.stereotype.Component;

@Component
public class QuestionBankRepository {

	public Question getQuestionById(@RequestParam String questionId) {
		return null;
	}

	public List<Question> getAllQuestions() {
		return null;
	}

	public String addQuestions(List<Question> questions) {
		return "success";
	}

}
