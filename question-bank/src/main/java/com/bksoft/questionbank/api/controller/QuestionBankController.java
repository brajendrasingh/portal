package com.bksoft.questionbank.api.controller;

import com.bksoft.questionbank.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
		Question question = QuestionMapper.INSTANCE.convertToApiQuestion(questionBankService.getQuestion())
		return ResponseEntity.ok().body(question);
	}

	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public ResponseEntity<List<Question>> getAllQuestions(){
		List<Question> questions = QuestionMapper.INSTANCE.convertToApiQuestion(questionBankService.getQuestion())
		return ResponseEntity.ok().body(questions);
	}

}
