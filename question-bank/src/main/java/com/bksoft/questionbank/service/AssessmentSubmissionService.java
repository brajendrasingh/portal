package com.bksoft.questionbank.service;

import com.bksoft.questionbank.api.models.AssessmentSubmissionRequest;
import com.bksoft.questionbank.entities.AssessmentSubmission;
import com.bksoft.questionbank.entities.QuestionResponse;
import com.bksoft.questionbank.repositories.AssessmentSubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AssessmentSubmissionService {

    private final AssessmentSubmissionRepository submissionRepo;

    public AssessmentSubmissionService(AssessmentSubmissionRepository submissionRepo) {
        this.submissionRepo = submissionRepo;
    }

    public AssessmentSubmission submit(AssessmentSubmissionRequest request) {

        // ⚠️ In real life, fetch correct answers from DB
        int total = request.answers.size();
        int correct = 0;

        AssessmentSubmission submission = new AssessmentSubmission();
        submission.setUserId(request.userId);
        submission.setAssessmentId(request.assessmentId);
        submission.setTimeTakenSeconds(request.timeTakenSeconds);
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setStatus(AssessmentSubmission.Status.EVALUATED);

        List<QuestionResponse> responses = new ArrayList<>();

        for (var ans : request.answers) {
            boolean isCorrect = evaluateAnswer(ans.questionId, ans.selectedAnswer);
            if (isCorrect) correct++;

            QuestionResponse r = new QuestionResponse();
            r.setQuestionId(ans.questionId);
            r.setSelectedAnswer(ans.selectedAnswer);
            r.setCorrect(isCorrect);
            r.setSubmission(submission);

            responses.add(r);
        }

        submission.setTotalQuestions(total);
        submission.setCorrectAnswers(correct);
        submission.setScore(correct * 1); // customize scoring
        submission.setResponses(responses);

        return submissionRepo.save(submission);
    }

    private boolean evaluateAnswer(Long questionId, String answer) {
        // placeholder logic
        return "A".equalsIgnoreCase(answer);
    }
}
