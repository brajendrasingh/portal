package com.bksoft.questionbank.api.controller;

import com.bksoft.questionbank.api.models.AssessmentSubmissionRequest;
import com.bksoft.questionbank.service.AssessmentSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessment")
public class AssessmentSubmissionController {

    private final AssessmentSubmissionService service;

    public AssessmentSubmissionController(AssessmentSubmissionService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitAssessment(@RequestBody AssessmentSubmissionRequest request) {
        return ResponseEntity.ok(service.submit(request));
    }

    @GetMapping("/submittedAnswerDetail")
    public ResponseEntity<?> getSubmittedAnswerDetail(@RequestParam String assessmentId, @RequestParam(required = false) String userId, @RequestParam(required = false) Integer attemptNo) {
        return ResponseEntity.ok(service.getSubmittedAnswerDetail(userId, assessmentId, attemptNo));
    }

    @GetMapping("/submissions")
    public ResponseEntity<?> getAllSubmissions(@RequestParam(required = false) String userId, @RequestParam(required = false) String assessmentId) {
        return ResponseEntity.ok(service.getAllSubmissions(userId, assessmentId));
    }
}
