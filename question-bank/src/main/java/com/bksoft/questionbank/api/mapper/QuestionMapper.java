package com.bksoft.questionbank.api.mapper;

import com.bksoft.questionbank.api.models.Question;
import java.util.List;
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
	@Mapping(target = "questionId", source = "questionId")
	@Mapping(target = "questionText", source = "questionText")
	@Mapping(target = "answer", source = "answer")
	Question convertToApiQuestion(com.bksoft.questionbank.dtos.Question question);

	@Mapping(target = "questionId", source = "questionId")
	@Mapping(target = "questionText", source = "questionText")
	@Mapping(target = "answer", source = "answer")
	com.bksoft.questionbank.dtos.Question convertToDtosQuestion(Question question);

	List<Question> convertToApiQuestion(List<com.bksoft.questionbank.dtos.Question> question);

	List<com.bksoft.questionbank.dtos.Question> convertToDtosQuestion(List<Question> question);

}
