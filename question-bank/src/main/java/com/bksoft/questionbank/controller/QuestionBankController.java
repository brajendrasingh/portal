package com.bksoft.questionbank.controller;


@RestController
public class QuestionBankController {

	@RequestMapping("/question")
	public String getQuestion(){
		return "question";
	}
}
