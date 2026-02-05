package com.bksoft.questionbank.api.controller;

import com.bksoft.questionbank.api.mapper.QuestionMapper;
import com.bksoft.questionbank.api.models.Question;
import com.bksoft.questionbank.service.QuestionBankService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionBankController {
	private static final Logger log = LoggerFactory.getLogger(QuestionBankController.class);

	@Autowired
	private QuestionBankService questionBankService;

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ResponseEntity<Question> getQuestionById(@RequestParam String questionId){
		log.info("Fetch question by question id");
		Question question = QuestionMapper.INSTANCE.convertToApiQuestion(questionBankService.getQuestionById(questionId));
		return ResponseEntity.ok().body(question);
	}

	@RequestMapping(value = "/allQuestions", method = RequestMethod.GET)
	public ResponseEntity<List<Question>> getAllQuestions(){
		log.info("Fetch all questions");
		List<Question> questions = QuestionMapper.INSTANCE.convertToApiQuestion(questionBankService.getAllQuestions());
		return ResponseEntity.ok().body(questions);
	}

	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public ResponseEntity<List<Question>> getQuestions(@RequestParam String examType, @RequestParam String subjectOrExam, @RequestParam String questionsType, @RequestParam String difficulty){
		log.info("Fetch all questions");
		List<Question> questions = QuestionMapper.INSTANCE.convertToApiQuestion(questionBankService.getQuestions(examType, subjectOrExam, questionsType, difficulty));
		return ResponseEntity.ok().body(questions);
	}

	@RequestMapping(value = "/questions", method = RequestMethod.POST)
	public ResponseEntity<String> addQuestions(@RequestBody List<Question> questionsRequest) {
		log.info("Add questions list");
		List<com.bksoft.questionbank.dtos.Question> questions = QuestionMapper.INSTANCE.convertToDtosQuestion(questionsRequest);
		return ResponseEntity.ok().body(questionBankService.addQuestions(questions));
	}

	@RequestMapping(value = "/question", method = RequestMethod.PUT)
	public ResponseEntity<String> updateQuestion(@RequestBody Question questionsRequest) {
		log.info("Update question by question id and payload");
		com.bksoft.questionbank.dtos.Question question = QuestionMapper.INSTANCE.convertToDtosQuestion(questionsRequest);
		return ResponseEntity.ok().body(questionBankService.updateQuestion(question));
	}

	@RequestMapping(value = "/question", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteQuestionById(@RequestParam String questionsId) {
		log.info("Delete question by question id");
		return ResponseEntity.ok().body(questionBankService.deleteQuestionById(questionsId));
	}

}
