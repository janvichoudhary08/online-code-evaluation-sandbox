https://online-code-evaluation-sandbox.onrender.com/

# Online Code Evaluation Sandbox

A backend-focused online code evaluation system inspired by coding judges.  
It compiles, executes, and evaluates user-submitted Java programs in a controlled environment.

## Features
- Compile and execute Java code dynamically
- Isolated execution workspace per submission
- Execution time measurement
- Time-limit enforcement to prevent infinite loops
- Output-based correctness evaluation (PASS / FAIL)
- Clean web UI for end-to-end demonstration

## Tech Stack
- Java
- Spring Boot
- REST APIs
- ProcessBuilder
- HTML, CSS, JavaScript

## How It Works
1. User submits source code, input, and expected output
2. Code is written to a temporary workspace
3. Java code is compiled and executed
4. Execution output is captured and evaluated
5. Result, verdict, and execution time are returned

## Run Locally
```bash
mvn spring-boot:run
