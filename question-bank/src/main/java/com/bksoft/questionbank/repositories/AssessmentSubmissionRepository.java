package com.bksoft.questionbank.repositories;

import com.bksoft.questionbank.entities.AssessmentSubmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssessmentSubmissionRepository extends JpaRepository<AssessmentSubmission, Long> {

    Optional<AssessmentSubmission> findByUserIdAndAssessmentIdAndAttemptNo(String userId, String assessmentId, Integer attemptNo);

    Optional<AssessmentSubmission> findTopByUserIdAndAssessmentIdOrderByAttemptNoDesc(String userId, String assessmentId);

    List<AssessmentSubmission> findByUserId(String userId);

    Page<AssessmentSubmission> findByUserId(String userId, Pageable pageable);

    List<AssessmentSubmission> findByAssessmentId(String assessmentId);

    Page<AssessmentSubmission> findByAssessmentId(String assessmentId, Pageable pageable);

    List<AssessmentSubmission> findByUserIdAndAssessmentId(String userId, String assessmentId);

    Page<AssessmentSubmission> findByUserIdAndAssessmentId(String userId, String assessmentId, Pageable pageable);

    List<AssessmentSubmission> findAllByOrderBySubmittedAtDesc();

    Page<AssessmentSubmission> findAllByOrderBySubmittedAtDesc(Pageable pageable);
}
