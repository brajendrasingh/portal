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
				.convertToDtosQuestion(questionRepository.findById(questionId).get());
		return question;
	}

	public List<Question> getAllQuestions() {
		List<com.bksoft.questionbank.dtos.Question> questions = QuestionEntityMapper.INSTANCE
				.convertToDtosQuestion(questionRepository.findAll());
		return questions;
	}

	public String addQuestions(List<Question> questions) {
		List<com.bksoft.questionbank.entities.QuestionEntity> questionsEntities = QuestionEntityMapper.INSTANCE
				.convertToQuestionEntity(questions);
		questionRepository.saveAll(questionsEntities);
		return "success";
	}

	public String updateQuestion(Question question) {
		com.bksoft.questionbank.entities.QuestionEntity questionEntity = QuestionEntityMapper.INSTANCE
				.convertToQuestionEntity(question);
		QuestionEntity response = questionRepository.save(questionEntity);
		return response.getQuestionId();
	}

	public String deleteQuestionById(String questionId) {
		questionRepository.deleteById(questionId);
		return "success";
	}
	
	public String deleteAllQuestions() {
		questionRepository.deleteAll();
		return "success";
	}

}