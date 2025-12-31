package com.example.codeevaluator.service;

import com.example.codeevaluator.model.CodeRequest;
import com.example.codeevaluator.model.CodeResponse;
import com.example.codeevaluator.util.FileUtil;
import com.example.codeevaluator.util.OutputEvaluator;   // already present
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    private final ExecutionService executionService;

    public SubmissionServiceImpl(ExecutionService executionService) {
        this.executionService = executionService;
    }

    @Override
    public CodeResponse submit(CodeRequest request) {

        CodeResponse response = new CodeResponse();

        try {
            Path workDir = FileUtil.createTempDirectory();

            FileUtil.writeSourceCode(workDir, request.getSourceCode());
            FileUtil.writeInput(workDir, request.getInput());
            FileUtil.prepareOutput(workDir);

            ExecutionResult execResult =
                    executionService.executeJava(workDir);

            // 🔹 CHANGED (Phase 6): timeout-aware error handling
            if (!execResult.isSuccess()) {
                response.setStatus("ERROR");

                if (execResult.isTimedOut()) {          // 🔹 ADDED
                    response.setErrorMessage("Time Limit Exceeded");
                    response.setVerdict("TLE");
                } else {
                    response.setErrorMessage(execResult.getError());
                }
                return response;
            }

            // evaluation logic (unchanged)
            boolean pass = OutputEvaluator.isOutputMatching(
                    execResult.getOutput(),
                    request.getExpectedOutput()
            );

            response.setStatus("SUCCESS");
            response.setVerdict(pass ? "PASS" : "FAIL");
            response.setOutput(execResult.getOutput());
            response.setExecutionTimeMs(execResult.getExecutionTimeMs()); // 🔹 ADDED


        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setErrorMessage(e.getMessage());
        }

        return response;
    }
}
