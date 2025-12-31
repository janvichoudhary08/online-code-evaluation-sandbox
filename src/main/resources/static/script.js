const runBtn = document.getElementById("runBtn");

const statusEl = document.getElementById("status");
const verdictEl = document.getElementById("verdict");
const outputEl = document.getElementById("output");

// 🔹 ADDED (Execution Metrics): reference for time display
const timeEl = document.getElementById("time");

runBtn.addEventListener("click", async () => {

    // Reset UI
    statusEl.textContent = "Running...";
    verdictEl.textContent = "—";
    verdictEl.className = "";
    outputEl.textContent = "Executing code...";

    // 🔹 ADDED (Execution Metrics): reset time
    timeEl.textContent = "—";

    const requestBody = {
        language: "java",
        sourceCode: document.getElementById("sourceCode").value,
        input: document.getElementById("input").value,
        expectedOutput: document.getElementById("expectedOutput").value
    };

    try {
        const response = await fetch("/api/execute", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        });

        const result = await response.json();

        // Update status
        statusEl.textContent = result.status || "—";

        if (result.status === "SUCCESS") {

            verdictEl.textContent = result.verdict;
            outputEl.textContent = result.output || "";

            // 🔹 ADDED (Execution Metrics): show execution time on SUCCESS
            if (result.executionTimeMs !== undefined) {
                timeEl.textContent = result.executionTimeMs + " ms";
            }

            if (result.verdict === "PASS") {
                verdictEl.classList.add("pass");
            } else if (result.verdict === "FAIL") {
                verdictEl.classList.add("fail");
            }

        } else {
            // ERROR cases (Compilation error, Runtime error, TLE)
            verdictEl.textContent = "ERROR";
            verdictEl.classList.add("error");
            outputEl.textContent = result.errorMessage || "Unknown error occurred";
        }

    } catch (err) {
        statusEl.textContent = "ERROR";
        verdictEl.textContent = "ERROR";
        verdictEl.classList.add("error");
        outputEl.textContent = "Failed to connect to backend";
    }
});
