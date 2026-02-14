package com.bksoft.questionbank.service;

import com.bksoft.questionbank.dtos.Question;
import com.bksoft.questionbank.dtos.i18n.QuestionRequestDTO;
import com.bksoft.questionbank.dtos.i18n.QuestionResponseDTO;
import com.bksoft.questionbank.dtos.i18n.TranslationDTO;
import com.bksoft.questionbank.entities.QuestionEntity;
import com.bksoft.questionbank.entities.i18n.QuestionOption;
import com.bksoft.questionbank.entities.i18n.QuestionTranslation;
import com.bksoft.questionbank.repositories.QuestionEntityRepository;
import com.bksoft.questionbank.repositories.i18n.QuestionRepository;
import com.bksoft.questionbank.repositories.i18n.QuestionTranslationRepository;
import com.bksoft.questionbank.utils.QuestionEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class QuestionBankService {
	private static final Logger log = LoggerFactory.getLogger(QuestionBankService.class);

	@Autowired
	private QuestionEntityRepository questionEntityRepository;
	@Autowired
	private QuestionTranslationRepository translationRepository;
	@Autowired
	private QuestionRepository questionRepository;

	public Question getQuestionById(String questionId) {
		log.info("Fetch question by question id");
		String lang = LocaleContextHolder.getLocale().getLanguage();
		com.bksoft.questionbank.dtos.Question question = QuestionEntityMapper.INSTANCE
				.convertToDtosQuestion(questionEntityRepository.findById(questionId).get());
		return question;
	}

	public List<Question> getAllQuestions() {
		log.info("Fetch all questions");
		List<com.bksoft.questionbank.dtos.Question> questions = QuestionEntityMapper.INSTANCE
				.convertToDtosQuestion(questionEntityRepository.findAll());
		return questions;
	}

	public List<Question> getQuestions(String examType, String subject, String questionsType, String difficulty) {
		log.info("Fetch questions by filters");
		List<com.bksoft.questionbank.dtos.Question> questions = QuestionEntityMapper.INSTANCE.convertToDtosQuestion(questionEntityRepository.findAll());
		List<Question> filteredResponse = new ArrayList<>();
		for (Question question : questions) {
			if (question.getSubject().equals(subject) && question.getCategory().name().equals(questionsType)) {
				filteredResponse.add(question);
			}
		}
		return filteredResponse;
	}

	public String addQuestions(List<Question> questions) {
		log.info("Add questions list");
		List<com.bksoft.questionbank.entities.QuestionEntity> questionsEntities = QuestionEntityMapper.INSTANCE
				.convertToQuestionEntity(questions);
		questionEntityRepository.saveAll(questionsEntities);
		return "success";
	}

	public String updateQuestion(Question question) {
		log.info("Update question by question id and payload");
		com.bksoft.questionbank.entities.QuestionEntity questionEntity = QuestionEntityMapper.INSTANCE
				.convertToQuestionEntity(question);
		QuestionEntity response = questionEntityRepository.save(questionEntity);
		return response.getQuestionId();
	}

	public String deleteQuestionById(String questionId) {
		log.info("Delete question by question id");
		questionEntityRepository.deleteById(questionId);
		return "success";
	}
	
	public String deleteAllQuestions() {
		log.info("Delete all questions");
		questionEntityRepository.deleteAll();
		return "success";
	}

	public void saveQuestion(List<QuestionRequestDTO> questions) {
		for (QuestionRequestDTO dto : questions) {
			com.bksoft.questionbank.entities.i18n.Question question = new com.bksoft.questionbank.entities.i18n.Question();
			question.setId(dto.getId());
			question.setSubject(dto.getSubject());
			question.setTopic(dto.getTopic());
			question.setCategory(dto.getCategory());
			question.setQuestionType(dto.getQuestionType());
			question.setDifficulty(dto.getDifficulty());
			question.setMarks(dto.getMarks());
			question.setCorrectIndex(dto.getCorrectIndex());

			List<QuestionTranslation> translations = new ArrayList<>();

			for (TranslationDTO t : dto.getTranslations()) {
				QuestionTranslation translation = new QuestionTranslation();
				translation.setLanguage(t.getLanguage());
				translation.setQuestionText(t.getQuestionText());
				translation.setExplanation(t.getExplanation());
				translation.setQuestion(question);

				List<QuestionOption> options = new ArrayList<>();

				for (int i = 0; i < t.getOptions().size(); i++) {
					QuestionOption option = new QuestionOption();
					option.setOptionIndex(i);
					option.setOptionText(t.getOptions().get(i));
					option.setTranslation(translation);
					options.add(option);
				}
				translation.setOptions(options);
				translations.add(translation);
			}
			question.setTranslations(translations);
			questionRepository.save(question);
		}
	}

	public QuestionResponseDTO getQuestion(String questionId) {
		String lang = LocaleContextHolder.getLocale().getLanguage();
		QuestionTranslation translation = translationRepository.findByQuestionIdAndLanguage(questionId, lang)
				.orElseGet(() -> translationRepository
						.findByQuestionIdAndLanguage(questionId, "en")
						.orElseThrow(() -> new RuntimeException("Question not found")));

		return QuestionResponseDTO.builder().questionId(questionId)
				.questionText(translation.getQuestionText())
				.answerOptions(translation.getOptions().stream()
						.sorted(Comparator.comparing(QuestionOption::getOptionIndex))
						.map(QuestionOption::getOptionText)
						.toList())
				.correctOptionIndex(translation.getQuestion().getCorrectIndex()).explanation(translation.getExplanation()).build();
	}

	public List<QuestionResponseDTO> getAllQuestionsByLanguage() {
		String lang = LocaleContextHolder.getLocale().getLanguage();
		List<QuestionTranslation> translations = translationRepository.findAllByLanguage(lang);

		// If no translations found → fallback to English
		if (translations.isEmpty() && !lang.equalsIgnoreCase("en")) {
			translations = translationRepository.findAllByLanguage("en");
		}
		return translations.stream().map(t -> QuestionResponseDTO.builder()
				.questionId(t.getQuestion().getId())
				.questionText(t.getQuestionText())
				.answerOptions(t.getOptions()
						.stream()
						.sorted(Comparator.comparing(QuestionOption::getOptionIndex))
						.map(QuestionOption::getOptionText)
						.toList()
				)
				.correctOptionIndex(t.getQuestion().getCorrectIndex()).explanation(t.getExplanation()).build()
		).toList();
	}

	public List<QuestionResponseDTO> getI18nQuestions(String examType, String subject, String questionsType, String difficulty) {
		log.info("Fetch i18n questions by filters");
		String lang = LocaleContextHolder.getLocale().getLanguage();
		List<QuestionTranslation> translations = translationRepository.findFilteredQuestions(subject, questionsType, difficulty, lang);
		// Fallback to English if nothing found
		if (translations.isEmpty() && !lang.equalsIgnoreCase("en")) {
			translations = translationRepository.findFilteredQuestions(subject, questionsType, difficulty, "en");
		}
		return translations.stream().map(t -> QuestionResponseDTO.builder()
				.questionId(t.getQuestion().getId()).questionText(t.getQuestionText())
				.answerOptions(t.getOptions().stream().sorted(Comparator.comparing(QuestionOption::getOptionIndex))
						.map(QuestionOption::getOptionText).toList()).correctOptionIndex(t.getQuestion().getCorrectIndex())
				.explanation(t.getExplanation()).build()).toList();
	}
}