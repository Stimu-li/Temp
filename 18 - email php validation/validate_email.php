<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST['email'];

    // Validate the email address
    if (filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo "<h2>The email address '$email' is valid.</h2>";
    } else {
        echo "<h2>The email address '$email' is invalid.</h2>";
    }
}