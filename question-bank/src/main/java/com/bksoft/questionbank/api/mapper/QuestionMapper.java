package com.bksoft.questionbank.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * The Interface QuestionMapper.
 */
@Mapper
public interface QuestionMapper {

	/** The instance. */
	QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

	/**
	 * Convert to api question.
	 *
	 * @param question the question
	 * @return the question
	 */
	@Mapping(target = "answer", source = "answer") 
	Question convertToApiQuestion(com.bksoft.questionbank.dtos.Question question);
}
