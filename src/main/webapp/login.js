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

            body: "username=" + encodeURIComponent(username) +
                  "&password=" + encodeURIComponent(password)
        });

        const result = await response.text();

        console.log("Response:", result);

        if (result.trim() === "Login Successful") {
			
			console.log(result);

            localStorage.setItem("username", username);
            localStorage.setItem("isLoggedIn", "true");

            window.location.replace("index.html");

        } else {

            document.getElementById("message").innerHTML =
                "Invalid Username or Password";
        }

    } catch (error) {

        console.error("Error:", error);

        document.getElementById("message").innerHTML =
            "Something went wrong. Please try again.";
    }

});