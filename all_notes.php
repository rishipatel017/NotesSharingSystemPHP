<?php
include 'config.php';
if (!isset($_SESSION['user_id'])) header("Location: index.php");

$sql = "SELECT notes.id, notes.title, notes.description, notes.filename, notes.filepath, 
               notes.uploaded_at, users.username 
        FROM notes 
        JOIN users ON notes.user_id = users.id 
        ORDER BY notes.uploaded_at DESC";

$result = $conn->query($sql);
?>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>All Notes</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
<script src="js/script.js" defer></script>
</head>
<body>

<?php include 'header.php'; ?>

<div class="main-content" id="main">
  <h2><i class="fa-solid fa-globe"></i> All Shared Notes</h2>
  <div class="cards-container">
    <?php while ($row = $result->fetch_assoc()): ?>
      <div class="note-card">
        <div class="note-icon">
          <?php
            $ext = strtolower(pathinfo($row['filename'], PATHINFO_EXTENSION));
            echo match($ext) {
              'pdf' => '<i class="fa-solid fa-file-pdf"></i>',
              'doc', 'docx' => '<i class="fa-solid fa-file-word"></i>',
              'ppt', 'pptx' => '<i class="fa-solid fa-file-powerpoint"></i>',
              default => '<i class="fa-solid fa-file-lines"></i>'
            };
          ?>
        </div>
        <div class="note-details">
          <h4><?= htmlspecialchars($row['title']) ?></h4>
          <p><b>By:</b> <?= htmlspecialchars($row['username']) ?></p>
          <p><b>Description:</b> <?= htmlspecialchars($row['description']) ?></p>
          <p><i class="fa-solid fa-calendar"></i> <?= date("M d, Y", strtotime($row['uploaded_at'])) ?></p>
        </div>
        <a href="<?= htmlspecialchars($row['filepath']) ?>" download class="btn-download">
          <i class="fa-solid fa-download"></i> Download
        </a>
      </div>
    <?php endwhile; ?>
  </div>
</div>

</body>
</html>
