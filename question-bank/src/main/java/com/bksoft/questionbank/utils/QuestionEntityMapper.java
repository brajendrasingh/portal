package com.bksoft.questionbank.utils;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionEntityMapper {

	QuestionEntityMapper INSTANCE = Mappers.getMapper(QuestionEntityMapper.class);

	@Mapping(target = "questionId", source = "questionId")
	@Mapping(target = "question", source = "questionText")
	@Mapping(target = "optionA", source = "answer.optionA")
	@Mapping(target = "optionB", source = "answer.optionB")
	@Mapping(target = "optionC", source = "answer.optionC")
	@Mapping(target = "optionD", source = "answer.optionD")
	@Mapping(target = "optionE", source = "answer.optionE")
	com.bksoft.questionbank.entities.QuestionEntity convertToQuestionEntity(
			com.bksoft.questionbank.dtos.Question question);

	@Mapping(target = "questionId", source = "questionId")
	@Mapping(target = "questionText", source = "question")
	@Mapping(target = "answer.optionA", source = "optionA")
	@Mapping(target = "answer.optionB", source = "optionB")
	@Mapping(target = "answer.optionC", source = "optionC")
	@Mapping(target = "answer.optionD", source = "optionD")
	@Mapping(target = "answer.optionE", source = "optionE")
	com.bksoft.questionbank.dtos.Question convertToDtosQuestion(
			com.bksoft.questionbank.entities.QuestionEntity question);
	
	List<com.bksoft.questionbank.entities.QuestionEntity> convertToQuestionEntity(
			List<com.bksoft.questionbank.dtos.Question> question);
	
	List<com.bksoft.questionbank.dtos.Question> convertToDtosQuestion(
			List<com.bksoft.questionbank.entities.QuestionEntity> question);

}
