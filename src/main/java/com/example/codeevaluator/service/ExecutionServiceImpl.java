package com.example.codeevaluator.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Service
public class ExecutionServiceImpl implements ExecutionService {

    @Override
    public ExecutionResult executeJava(Path workDir) throws Exception {

        ExecutionResult result = new ExecutionResult();

        // ---------- STEP 1: COMPILE ----------
        ProcessBuilder compilePb =
                new ProcessBuilder("javac", "Main.java");
        compilePb.directory(workDir.toFile());

        Process compileProcess = compilePb.start();
        int compileExitCode = compileProcess.waitFor();

        if (compileExitCode != 0) {
            result.setSuccess(false);
            result.setError(readStream(compileProcess.getErrorStream()));
            return result;
        }

        // ---------- STEP 2: RUN ----------
        ProcessBuilder runPb =
                new ProcessBuilder("java", "Main");
        runPb.directory(workDir.toFile());

        // redirect stdin
        runPb.redirectInput(workDir.resolve("input.txt").toFile());

        Process runProcess = runPb.start();

        // 🔹 ADDED (Execution Metrics): start time BEFORE execution
        long startTime = System.currentTimeMillis();

        // 🔹 EXISTING (Phase 6): timeout handling
        boolean finished = runProcess.waitFor(2, TimeUnit.SECONDS);

        if (!finished) {
            runProcess.destroyForcibly();
            result.setSuccess(false);
            result.setTimedOut(true);
            result.setError("Execution timed out");
            return result;
        }

        int runExitCode = runProcess.exitValue();

        if (runExitCode != 0) {
            result.setSuccess(false);
            result.setError(readStream(runProcess.getErrorStream()));
            return result;
        }

        // ---------- STEP 3: CAPTURE OUTPUT ----------
        result.setSuccess(true);
        result.setOutput(readStream(runProcess.getInputStream()));

        // 🔹 ADDED (Execution Metrics): end time AFTER execution
        long endTime = System.currentTimeMillis();
        result.setExecutionTimeMs(endTime - startTime);

        return result;
    }

    private String readStream(java.io.InputStream stream) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
