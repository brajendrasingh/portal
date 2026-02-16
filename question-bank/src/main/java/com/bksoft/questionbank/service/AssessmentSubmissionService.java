package com.bksoft.questionbank.service;

import com.bksoft.questionbank.api.models.AssessmentSubmissionRequest;
import com.bksoft.questionbank.api.models.SubmittedAnswerDetail;
import com.bksoft.questionbank.entities.AssessmentSubmission;
import com.bksoft.questionbank.entities.QuestionEntity;
import com.bksoft.questionbank.entities.QuestionResponse;
import com.bksoft.questionbank.entities.i18n.Question;
import com.bksoft.questionbank.repositories.AssessmentSubmissionRepository;
import com.bksoft.questionbank.repositories.QuestionEntityRepository;
import com.bksoft.questionbank.repositories.i18n.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssessmentSubmissionService {

    private final QuestionRepository questionRepository;
    private final QuestionEntityRepository questionEntityRepository;
    private final AssessmentSubmissionRepository submissionRepo;

    public AssessmentSubmissionService(AssessmentSubmissionRepository submissionRepo, QuestionEntityRepository questionEntityRepository, QuestionRepository questionRepository) {
        this.submissionRepo = submissionRepo;
        this.questionRepository = questionRepository;
        this.questionEntityRepository = questionEntityRepository;
    }

    public AssessmentSubmission submit(AssessmentSubmissionRequest request) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        int total = request.questionAnswers.size();
        int correct = 0;

        AssessmentSubmission submission = new AssessmentSubmission();
        submission.setUserId(request.userId);
        submission.setAssessmentId(UUID.randomUUID().toString().replace("-", ""));
        submission.setAttemptNo(request.attemptNo);
        submission.setTimeTakenSeconds(request.timeTakenSeconds);
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setStatus(AssessmentSubmission.Status.EVALUATED);

        List<QuestionResponse> responses = new ArrayList<>();

        for (var qna : request.questionAnswers) {
            //QuestionEntity qe = questionEntityRepository.findById(qna.questionId).orElseThrow(() -> new EntityNotFoundException("Question not found"));
            Question q = questionRepository.findById(qna.questionId).orElseThrow(() -> new EntityNotFoundException("Question not found"));
            boolean isCorrect = evaluateAnswer(List.of(q.getTranslations().get(0).getOptions().get(q.getCorrectIndex()).getOptionText()), qna.selectedAnswers);

            if (isCorrect) correct++;

            QuestionResponse r = new QuestionResponse();
            r.setQuestionId(qna.questionId);
            r.setQuestionType(qna.questionType);
            r.setSelectedAnswers(qna.selectedAnswers);
            r.setCorrect(isCorrect);
            r.setSubmission(submission);

            responses.add(r);
        }

        submission.setTotalQuestions(total);
        submission.setCorrectAnswers(correct);
        submission.setScore(correct * 1); // customize scoring
        submission.setPassed(true);
        submission.setEvaluatedBy("AUTO");
        submission.setEvaluatedAt(LocalDateTime.now());
        submission.setResponses(responses);

        return submissionRepo.save(submission);
    }

    private boolean evaluateAnswer(List<String> correctAnswers, List<String> selectedAnswers) {
        if (correctAnswers == null || selectedAnswers == null) {
            return false;
        }
        if (correctAnswers.size() != selectedAnswers.size()) {
            return false;
        }
        Set<String> correctSet = correctAnswers.stream().map(String::toLowerCase).collect(Collectors.toSet());

        Set<String> selectedSet = selectedAnswers.stream().map(String::toLowerCase).collect(Collectors.toSet());

        return correctSet.equals(selectedSet);
    }

    public SubmittedAnswerDetail getSubmittedAnswerDetail(String userId, String assessmentId, Integer attemptNo) {
        SubmittedAnswerDetail submittedAnswerDetail = new SubmittedAnswerDetail();
        AssessmentSubmission res = submissionRepo.findByAssessmentId(assessmentId).get(0);

        submittedAnswerDetail.userId = res.getUserId();
        submittedAnswerDetail.assessmentId = res.getAssessmentId();
        submittedAnswerDetail.timeTakenSeconds = res.getTimeTakenSeconds();
        submittedAnswerDetail.attemptNo = res.getAttemptNo();
        submittedAnswerDetail.totalQuestions = res.getTotalQuestions();
        submittedAnswerDetail.correctAnswers = res.getCorrectAnswers();
        submittedAnswerDetail.submittedAt = res.getSubmittedAt();

        for (QuestionResponse qr : res.getResponses()) {
            SubmittedAnswerDetail.QuestionAnswer qa = new SubmittedAnswerDetail.QuestionAnswer();
            //QuestionEntity qe = questionEntityRepository.findById(qr.getQuestionId()).orElseThrow(() -> new EntityNotFoundException("Question not found"));
            Question q = questionRepository.findById(qr.getQuestionId()).orElseThrow(() -> new EntityNotFoundException("Question not found"));
            qa.questionId = qr.getQuestionId();
            qa.questionText = q.getTranslations().get(0).getQuestionText();
            qa.correctAnswers = List.of(q.getTranslations().get(0).getOptions().get(q.getCorrectIndex()).getOptionText());
            qa.questionType = qr.getQuestionType();
            qa.selectedAnswers = qr.getSelectedAnswers();
            submittedAnswerDetail.questionAnswers.add(qa);
        }
        return submittedAnswerDetail;
    }

    public List<AssessmentSubmission> getAllSubmissions(String userId, String assessmentId) {

        if (userId != null && assessmentId != null) {
            return submissionRepo.findByUserIdAndAssessmentId(userId, assessmentId);
        }

        if (userId != null) {
            return submissionRepo.findByUserId(userId);
        }

        if (assessmentId != null) {
            return submissionRepo.findByAssessmentId(assessmentId);
        }

        return submissionRepo.findAllByOrderBySubmittedAtDesc();
    }
}
