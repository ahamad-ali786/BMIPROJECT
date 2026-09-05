
document.getElementById("loginForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch("./login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body:
                "username=" + encodeURIComponent(username) +
                "&password=" + encodeURIComponent(password)
        });

        const result = await response.text();
		
		
		console.log(result);

        if (result.trim() === "Login Successful") {
			
			console.log("SERVER RESPONSE:", JSON.stringify(result));

            console.log("LOGIN SUCCESSFUL");

            localStorage.setItem("username", username);
            localStorage.setItem("isLoggedIn", "true");

            document.getElementById("message").innerText =
                "Login Successful!";

            setTimeout(function() {
                window.location.href = "./index.html";
            }, 500);

        } else {

            console.log("LOGIN FAILED");

            document.getElementById("message").innerText =
                "Invalid Username or Password";
        }

    } catch (error) {

        console.error("ERROR:", error);

        document.getElementById("message").innerText =
            "Something went wrong. Please try again.";
    }

});

