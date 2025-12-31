package com.example.codeevaluator.controller;

import com.example.codeevaluator.model.CodeRequest;
import com.example.codeevaluator.model.CodeResponse;
import com.example.codeevaluator.service.SubmissionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CodeExecutionController {

    private final SubmissionService submissionService;

    public CodeExecutionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/execute")
    public CodeResponse execute(@RequestBody CodeRequest request) {
        return submissionService.submit(request);
    }
}
