package com.bksoft.questionbank.repositories;

import com.bksoft.questionbank.entities.AssessmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssessmentSubmissionRepository extends JpaRepository<AssessmentSubmission, Long> {

    Optional<AssessmentSubmission> findByUserIdAndAssessmentIdAndAttemptNo(String userId, String assessmentId, Integer attemptNo);

    Optional<AssessmentSubmission> findTopByUserIdAndAssessmentIdOrderByAttemptNoDesc(String userId, String assessmentId);

    List<AssessmentSubmission> findByUserId(String userId);

    List<AssessmentSubmission> findByAssessmentId(String assessmentId);

    List<AssessmentSubmission> findByUserIdAndAssessmentId(String userId, String assessmentId);

    List<AssessmentSubmission> findAllByOrderBySubmittedAtDesc();
}
