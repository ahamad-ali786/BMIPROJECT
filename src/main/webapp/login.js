document.getElementById("loginForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch("login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body:
                "username=" + encodeURIComponent(username) +
                "&password=" + encodeURIComponent(password)
        });

        const result = await response.text();

        console.log("Response:", result);
        console.log("Trimmed Response:", result.trim());

        if (result.trim() === "SUCCESS") {

            console.log("LOGIN SUCCESS");

            localStorage.setItem("username", username);
            localStorage.setItem("isLoggedIn", "true");

            window.location.href = "index.html";

        } else {

            console.log("LOGIN FAILED");

            document.getElementById("message").innerText =
                "Invalid Username or Password";
        }

    } catch (error) {

        console.error("Error:", error);

        document.getElementById("message").innerText =
            "Something went wrong. Please try again.";
    }

});