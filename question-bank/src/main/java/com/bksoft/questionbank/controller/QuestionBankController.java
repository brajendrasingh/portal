package com.bksoft.questionbank.controller;

import com.bksoft.questionbank.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class QuestionBankController {
    
	@Autowired
    private QuestionBankService questionBankService;

	@RequestMapping("/question")
	public String getQuestion(){
		return questionBankService.getQuestion();
	}
}
