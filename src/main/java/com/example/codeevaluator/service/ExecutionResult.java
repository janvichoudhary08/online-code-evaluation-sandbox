package com.example.codeevaluator.service;

public class ExecutionResult {

    private boolean success;
    private String output;
    private String error;
    private boolean timedOut;

    // 🔹 ADDED (Execution Metrics)
    private long executionTimeMs;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getOutput() { return output; }
    public void setOutput(String output) { this.output = output; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public boolean isTimedOut() { return timedOut; }
    public void setTimedOut(boolean timedOut) { this.timedOut = timedOut; }

    // 🔹 ADDED (Execution Metrics)
    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    // 🔹 ADDED (Execution Metrics)
    public void setExecutionTimeMs(long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }
}

