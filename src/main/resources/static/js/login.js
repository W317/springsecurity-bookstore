document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        fetch('/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                username: username,
                password: password
            })
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = '/home.html'; // Redirect to home.html on successful login
                    return response.text().then(text => {
                        document.getElementById('responseMessage').innerText = 'Login ok: ' + text;
                    });
                } else {
                    return response.text().then(text => {
                        document.getElementById('responseMessage').innerText = 'Login failed: ' + text;
                    });
                }
            })
            .catch(error => {
                document.getElementById('responseMessage').innerText = 'Error: ' + error.message;
            });
    });
});
