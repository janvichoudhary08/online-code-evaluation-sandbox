package com.example.codeevaluator.service;

import java.nio.file.Path;

public interface ExecutionService {

    ExecutionResult executeJava(Path workDir) throws Exception;

}
