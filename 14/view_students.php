<?php
include 'db.php';

$sql = "SELECT * FROM students";
$result = $conn->query($sql);
?>

<h1>Student List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <?php while ($row = $result->fetch_assoc()): ?>
        <tr>
            <td><?= $row['id'] ?></td>
            <td><?= $row['name'] ?></td>
            <td><?= $row['email'] ?></td>
        </tr>
    <?php endwhile; ?>
</table>