<?php
$host = 'localhost';
$db = 'product_opinions';
$user = 'root'; // Update with your username
$pass = ''; // Update with your password

$conn = new mysqli($host, $user, $pass, $db);
if ($conn->connect_error) die("Connection failed: " . $conn->connect_error);
