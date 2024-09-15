package com.bksoft.questionbank.utils;

import com.bksoft.questionbank.api.models.Question;
import com.bksoft.questionbank.entities.QuestionEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionEntityMapper {

	QuestionEntityMapper INSTANCE = Mappers.getMapper(QuestionEntityMapper.class);

	@Mapping(target = "questionId", source = "questionId")
	@Mapping(target = "question", source = "question")
	com.bksoft.questionbank.entities.QuestionEntity convertToQuestionEntity(
			com.bksoft.questionbank.dtos.Question question);

	@Mapping(target = "questionId", source = "questionId")
	@Mapping(target = "question", source = "question")
	com.bksoft.questionbank.dtos.Question convertToDtosQuestion(
			com.bksoft.questionbank.entities.QuestionEntity question);

}
