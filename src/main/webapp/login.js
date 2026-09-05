
console.log(" login loaded");
document.getElementById("loginForm").addEventListener("submit", async function(event) {
	
	console.log("login button clicked");

    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch("./login", {
            method: "POST",

            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },

            body: "username=" + encodeURIComponent(username) +
                  "&password=" + encodeURIComponent(password)
        });
        const result = await response.text();

        console.log("SERVER RESPONSE:", result);
        console.log("TRIMMED RESPONSE:", result.trim());

		if (result.trim() === "SUCCESS") {

            console.log("LOGIN SUCCESS - REDIRECTING");

            localStorage.setItem("username", username);
            localStorage.setItem("isLoggedIn", "true");

            window.location.href = "./index.html";

        } else {

            console.log("LOGIN FAILED");

            document.getElementById("message").innerHTML =
                "Invalid Username or Password";
        }

    } catch (error) {

        console.error("ERROR:", error);

        document.getElementById("message").innerHTML =
            "Something went wrong. Please try again.";
    }

});