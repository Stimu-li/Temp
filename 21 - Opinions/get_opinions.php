<?php
include 'db.php';

$result = $conn->query("SELECT * FROM opinions ORDER BY timestamp DESC LIMIT 500");
$opinions = $result->fetch_all(MYSQLI_ASSOC);
echo json_encode($opinions);
$conn->close();
