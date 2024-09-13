package com.bksoft.questionbank.api.controller;

import com.bksoft.questionbank.api.mapper.QuestionMapper;
import com.bksoft.questionbank.api.models.Question;
import com.bksoft.questionbank.service.QuestionBankService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionBankController {

	@Autowired
	private QuestionBankService questionBankService;

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ResponseEntity<Question> getQuestionById(@RequestParam String questionId){
		Question question = QuestionMapper.INSTANCE.convertToApiQuestion(questionBankService.getQuestionById(questionId))
		return ResponseEntity.ok().body(question);
	}

	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public ResponseEntity<List<Question>> getAllQuestions(){
		List<Question> questions = QuestionMapper.INSTANCE.convertToApiQuestion(questionBankService.getAllQuestions())
		return ResponseEntity.ok().body(questions);
	}

	@RequestMapping(value = "/questions", method = RequestMethod.POST)
	public ResponseEntity<String> addQuestions(@RequestBody List<Question> questionsRequest) {
		List<com.bksoft.questionbank.dtos.Question> questions = QuestionMapper.INSTANCE.convertToApiQuestion(questionsRequest)
		return ResponseEntity.ok().body(questionBankService.addQuestions(questionsRequest));
	}

}
