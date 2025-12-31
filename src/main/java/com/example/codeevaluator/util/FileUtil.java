package com.example.codeevaluator.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    // Create a temp directory per submission
    public static Path createTempDirectory() throws IOException {
        return Files.createTempDirectory("code-exec-");
    }

    // Write source code to Main.java
    public static Path writeSourceCode(Path dir, String sourceCode) throws IOException {
        Path sourceFile = dir.resolve("Main.java");
        Files.writeString(sourceFile, sourceCode);
        return sourceFile;
    }

    // Write input to input.txt
    public static Path writeInput(Path dir, String input) throws IOException {
        Path inputFile = dir.resolve("input.txt");
        Files.writeString(inputFile, input == null ? "" : input);
        return inputFile;
    }

    // Prepare output.txt (empty initially)
    public static Path prepareOutput(Path dir) throws IOException {
        Path outputFile = dir.resolve("output.txt");
        Files.createFile(outputFile);
        return outputFile;
    }
}
