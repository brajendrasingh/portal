package com.bksoft.questionbank.service;

import com.bksoft.questionbank.dtos.Question;
import com.bksoft.questionbank.entities.QuestionEntity;
import com.bksoft.questionbank.repositories.QuestionRepository;
import com.bksoft.questionbank.utils.QuestionEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionBankService {
	private static final Logger log = LoggerFactory.getLogger(QuestionBankService.class);

	@Autowired
	private QuestionRepository questionRepository;

	public Question getQuestionById(String questionId) {
		log.info("Fetch question by question id");
		com.bksoft.questionbank.dtos.Question question = QuestionEntityMapper.INSTANCE
				.convertToDtosQuestion(questionRepository.findById(questionId).get());
		return question;
	}

	public List<Question> getAllQuestions() {
		log.info("Fetch all questions");
		List<com.bksoft.questionbank.dtos.Question> questions = QuestionEntityMapper.INSTANCE
				.convertToDtosQuestion(questionRepository.findAll());
		return questions;
	}

	public List<Question> getQuestions(String examType, String subject, String questionsType, String difficulty) {
		log.info("Fetch questions by filters");
		List<com.bksoft.questionbank.dtos.Question> questions = QuestionEntityMapper.INSTANCE.convertToDtosQuestion(questionRepository.findAll());
		List<Question> filteredResponse = new ArrayList<>();
		for (Question question : questions) {
			if ((question.getTags() != null && question.getTags().contains(examType)) || question.getSubject().equals(subject) || question.getCategory().name().equals(questionsType) || question.getDifficulty().name().equals(difficulty)) {
				filteredResponse.add(question);
			}
		}
		return filteredResponse;
	}

	public String addQuestions(List<Question> questions) {
		log.info("Add questions list");
		List<com.bksoft.questionbank.entities.QuestionEntity> questionsEntities = QuestionEntityMapper.INSTANCE
				.convertToQuestionEntity(questions);
		questionRepository.saveAll(questionsEntities);
		return "success";
	}

	public String updateQuestion(Question question) {
		log.info("Update question by question id and payload");
		com.bksoft.questionbank.entities.QuestionEntity questionEntity = QuestionEntityMapper.INSTANCE
				.convertToQuestionEntity(question);
		QuestionEntity response = questionRepository.save(questionEntity);
		return response.getQuestionId();
	}

	public String deleteQuestionById(String questionId) {
		log.info("Delete question by question id");
		questionRepository.deleteById(questionId);
		return "success";
	}
	
	public String deleteAllQuestions() {
		log.info("Delete all questions");
		questionRepository.deleteAll();
		return "success";
	}

}