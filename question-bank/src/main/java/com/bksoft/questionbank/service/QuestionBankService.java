package com.bksoft.questionbank.service;

import com.bksoft.questionbank.repositories.QuestionBankRepository;
import com.bksoft.questionbank.dtos.Question;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionBankService {

	@Autowired
	private QuestionBankRepository questionBankRepository;

	public Question getQuestionById(String questionId) {
		return questionBankRepository.getQuestionById(questionId);
	}

	public List<Question> getAllQuestions() {
		return questionBankRepository.getAllQuestions();
	}

	public String addQuestions(List<Question> questions) {
		return questionBankRepository.addQuestions(questions);
	}
}