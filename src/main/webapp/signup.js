document.getElementById("signupForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    console.log("Username:", username);
    console.log("Password:", password);

    const data = new URLSearchParams();

    data.append("username", username);
    data.append("password", password);

    try {

        const response = await fetch("signup", {
            method: "POST",

            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },

            body: data.toString()
        });

        const result = await response.text();

        console.log(result);

        if (result.trim() === "SUCCESS") {

            window.location.href = "login.html";

        } else {

            document.getElementById("message").innerHTML =
                "Signup failed";
        }

    } catch (error) {

        console.error(error);

        document.getElementById("message").innerHTML =
            "Something went wrong";
    }

});;