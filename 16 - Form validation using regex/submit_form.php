<?php
// Database connection 
$host = 'localhost';
$db = 'user_data';
$user = 'root'; // Update with your database username 
$pass = ''; // Update with your database password 

$conn = new mysqli($host, $user, $pass, $db);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Get form data 
$name = $_POST['name'];
$email = $_POST['email'];
$phone = $_POST['phone'];

// Regular expression validation 
$name_pattern = "/^[a-zA-Z\s]+$/";
$email_pattern = "/^[\w\.-]+@([\w-]+\.)+[\w-]{2,}$/"; // Updated pattern 
$phone_pattern = "/^\d{10}$/"; // Assuming 10-digit phone number 

$errors = [];

// Validate name 
if (!preg_match($name_pattern, $name)) {
    $errors[] = "Invalid name format.";
}

// Validate email 
if (!preg_match($email_pattern, $email)) {
    $errors[] = "Invalid email format.";
}

// Validate phone (optional) 
if ($phone && !preg_match($phone_pattern, $phone)) {
    $errors[] = "Invalid phone number format. Use 10 digits.";
}

// Check for errors 
if (count($errors) > 0) {
    foreach ($errors as $error) {
        echo "<p>$error</p>";
    }
} else {
    // Prepare and bind 
    $stmt = $conn->prepare("INSERT INTO users (name, email, phone) VALUES (?, ?, ?)");
    $stmt->bind_param("sss", $name, $email, $phone);

    // Execute the statement 
    if ($stmt->execute()) {
        echo "<h2>Data submitted successfully!</h2>";
    } else {
        echo "<h2>Error: " . $stmt->error . "</h2>";
    }
    $stmt->close();
}

$conn->close();
