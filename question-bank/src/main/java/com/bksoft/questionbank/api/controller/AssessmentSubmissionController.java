package com.bksoft.questionbank.api.controller;

import com.bksoft.questionbank.api.models.AssessmentSubmissionRequest;
import com.bksoft.questionbank.service.AssessmentSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
public class AssessmentSubmissionController {

    private final AssessmentSubmissionService service;

    public AssessmentSubmissionController(AssessmentSubmissionService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submit(@RequestBody AssessmentSubmissionRequest request) {
        return ResponseEntity.ok(service.submit(request));
    }
}
