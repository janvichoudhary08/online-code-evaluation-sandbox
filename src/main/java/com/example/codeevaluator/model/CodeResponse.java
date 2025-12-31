package com.example.codeevaluator.model;

public class CodeResponse {

    private String status;
    private String verdict;
    private String output;
    private String errorMessage;

    // 🔹 ADDED (Execution Metrics)
    private Long executionTimeMs;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerdict() {
        return verdict;
    }
    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getOutput() {
        return output;
    }
    public void setOutput(String output) {
        this.output = output;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // 🔹 ADDED (Execution Metrics)
    public Long getExecutionTimeMs() {
        return executionTimeMs;
    }

    // 🔹 ADDED (Execution Metrics)
    public void setExecutionTimeMs(Long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }
}
