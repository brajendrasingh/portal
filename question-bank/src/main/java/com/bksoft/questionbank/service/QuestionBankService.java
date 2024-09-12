package com.bksoft.questionbank.service;

import com.bksoft.questionbank.dao.QuestionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionBankService {

	@Autowired
    private QuestionBankRepository questionBankRepository;

	public String getQuestion(){
		return questionBankRepository.getQuestion();
	}

}