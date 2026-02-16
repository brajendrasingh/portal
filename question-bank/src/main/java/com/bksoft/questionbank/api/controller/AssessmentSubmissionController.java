package com.bksoft.questionbank.api.controller;

import com.bksoft.questionbank.api.models.AssessmentSubmissionRequest;
import com.bksoft.questionbank.service.AssessmentSubmissionService;
import com.bksoft.questionbank.utils.JwtTokenValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessment")
public class AssessmentSubmissionController {

    private final JwtTokenValidator jwtTokenValidator;
    private final AssessmentSubmissionService service;

    public AssessmentSubmissionController(AssessmentSubmissionService service, JwtTokenValidator jwtTokenValidator) {
        this.service = service;
        this.jwtTokenValidator = jwtTokenValidator;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitAssessment(@RequestHeader("Authorization") String authHeader, @RequestBody AssessmentSubmissionRequest request) {
        String userName = jwtTokenValidator.extractUsername(authHeader.replace("Bearer ",""));
        request.userId = userName;
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
