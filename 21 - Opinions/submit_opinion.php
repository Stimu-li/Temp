<?php
include 'db.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $stmt = $conn->prepare("INSERT INTO opinions (product_name, opinion) VALUES (?, ?)");
    $stmt->bind_param("ss", $_POST['product_name'], $_POST['opinion']);
    $stmt->execute();
    $stmt->close();
}
$conn->close();
