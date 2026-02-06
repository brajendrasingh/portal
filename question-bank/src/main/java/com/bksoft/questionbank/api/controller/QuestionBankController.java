package com.bksoft.questionbank.api.controller;

import com.bksoft.questionbank.api.mapper.QuestionMapper;
import com.bksoft.questionbank.api.models.Question;
import com.bksoft.questionbank.api.response.ApiResponse;
import com.bksoft.questionbank.api.response.Meta;
import com.bksoft.questionbank.service.QuestionBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public ResponseEntity<List<Question>> getQuestions(@RequestParam(required = false) String examType, @RequestParam(required = false) String subject, @RequestParam(required = false) String questionsType, @RequestParam(required = false) String difficulty){
		log.info("Fetch questions by filters");
		List<Question> questions = QuestionMapper.INSTANCE.convertToApiQuestion(questionBankService.getQuestions(examType, subject, questionsType, difficulty));
		return ResponseEntity.ok().body(questions);
	}

	@RequestMapping(value = "/v1/questions", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse<Map<String, List<Question>>>> getQuestionsV1(@RequestParam(required = false) String examType, @RequestParam(required = false) Set<String> subjects, @RequestParam(required = false) String questionsType, @RequestParam(required = false) String difficulty) {
		log.info("Fetch list of questions by filters");
		if (examType.equalsIgnoreCase("Govt")) {
			subjects = Set.of("English", "Math", "Science", "History");
			questionsType = "MCQ";
			difficulty = "";
		}
		ApiResponse<Map<String, List<Question>>> response = new ApiResponse<>();
		Map<String, List<Question>> map = new HashMap<>();
		int totalQuestions = 0;
		if (subjects != null && !subjects.isEmpty()) {
			for (String subject : subjects) {
				List<Question> questions = QuestionMapper.INSTANCE.convertToApiQuestion(questionBankService.getQuestions(examType, subject, questionsType, difficulty));
				map.put(subject, questions);
				totalQuestions += questions.size();
			}
		}
		response.setData(map);
		response.setMeta(new Meta(subjects != null ? subjects.size() : 0, totalQuestions, 1, totalQuestions));
		return ResponseEntity.ok().body(response);
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
