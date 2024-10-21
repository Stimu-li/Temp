<?php
$host = 'localhost';
$db = 'college_management';
$user = 'root'; // Change if you set a different username
$pass = ''; // Change if you set a different password

$conn = new mysqli($host, $user, $pass, $db);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
