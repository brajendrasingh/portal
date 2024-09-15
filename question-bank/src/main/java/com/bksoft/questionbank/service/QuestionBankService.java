package com.bksoft.questionbank.service;

import com.bksoft.questionbank.repositories.QuestionRepository;
import com.bksoft.questionbank.dtos.Question;
import com.bksoft.questionbank.entities.QuestionEntity;
import com.bksoft.questionbank.utils.QuestionEntityMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionBankService {

	@Autowired
	private QuestionRepository questionRepository;

	public Question getQuestionById(String questionId) {
		com.bksoft.questionbank.dtos.Question question = QuestionEntityMapper.INSTANCE
				.convertToDtosQuestion(questionRepository.findById(questionId));
		return question;
	}

	public List<Question> getAllQuestions() {
		List<com.bksoft.questionbank.dtos.Question> questions = QuestionEntityMapper.INSTANCE
				.convertToDtosQuestion(questionRepository.findAll());
		return questions;
	}

	public String addQuestions(List<Question> questions) {
		List<com.bksoft.questionbank.entities.QuestionEntity> questions = QuestionEntityMapper.INSTANCE
				.convertToQuestionEntity(questions);
		return questionRepository.saveAll(questions);
	}

	public String updateQuestion(Question questions) {
		return null;
	}

	public String deleteQuestionById(String questionsId) {
		return null;
	}

}