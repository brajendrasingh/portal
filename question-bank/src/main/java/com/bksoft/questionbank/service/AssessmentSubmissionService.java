package com.bksoft.questionbank.service;

import com.bksoft.questionbank.api.models.AssessmentSubmissionRequest;
import com.bksoft.questionbank.entities.AssessmentSubmission;
import com.bksoft.questionbank.entities.QuestionEntity;
import com.bksoft.questionbank.entities.QuestionResponse;
import com.bksoft.questionbank.repositories.AssessmentSubmissionRepository;
import com.bksoft.questionbank.repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssessmentSubmissionService {

    private final QuestionRepository questionRepository;
    private final AssessmentSubmissionRepository submissionRepo;

    public AssessmentSubmissionService(AssessmentSubmissionRepository submissionRepo, QuestionRepository questionRepository) {
        this.submissionRepo = submissionRepo;
        this.questionRepository = questionRepository;
    }

    public AssessmentSubmission submit(AssessmentSubmissionRequest request) {
        int total = request.questionAnswers.size();
        int correct = 0;

        AssessmentSubmission submission = new AssessmentSubmission();
        submission.setUserId(request.userId);
        submission.setAssessmentId(request.assessmentId);
        submission.setAttemptNo(request.attemptNo);
        submission.setTimeTakenSeconds(request.timeTakenSeconds);
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setStatus(AssessmentSubmission.Status.EVALUATED);

        List<QuestionResponse> responses = new ArrayList<>();

        for (var qna : request.questionAnswers) {
            QuestionEntity q = questionRepository.findById(qna.questionId).orElseThrow(() -> new EntityNotFoundException("Question not found"));
            boolean isCorrect = evaluateAnswer(List.of(q.getCorrectAnswer()), qna.selectedAnswers);

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

    public AssessmentSubmission getSubmission(String userId, String assessmentId, Integer attemptNo) {
        if (attemptNo != null) {
            return submissionRepo.findByUserIdAndAssessmentIdAndAttemptNo(userId, assessmentId, attemptNo)
                    .orElseThrow(() -> new RuntimeException("Submission not found"));
        }

        // latest attempt
        return submissionRepo.findTopByUserIdAndAssessmentIdOrderByAttemptNoDesc(userId, assessmentId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
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

        return submissionRepo.findAll();
    }
}
