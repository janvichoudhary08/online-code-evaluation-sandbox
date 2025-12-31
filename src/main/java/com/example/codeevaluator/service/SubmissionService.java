package com.example.codeevaluator.service;

import com.example.codeevaluator.model.CodeRequest;
import com.example.codeevaluator.model.CodeResponse;

public interface SubmissionService {
    CodeResponse submit(CodeRequest request);
}
