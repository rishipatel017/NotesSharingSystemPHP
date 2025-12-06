<?php
include 'config.php';
if (!isset($_SESSION['user_id'])) header("Location: index.php");

$user_id = $_SESSION['user_id'];

// Delete note
if (isset($_GET['delete'])) {
    $note_id = (int)$_GET['delete'];

    $stmt = $conn->prepare("DELETE FROM notes WHERE id = ? AND user_id = ?");
    $stmt->bind_param("ii", $note_id, $user_id);
    $stmt->execute();
    $stmt->close();

    header("Location: dashboard.php");
    exit();
}

// Fetch user’s notes
$stmt = $conn->prepare("SELECT id, title, description, filename, filepath, uploaded_at 
                        FROM notes 
                        WHERE user_id = ? 
                        ORDER BY uploaded_at DESC");
$stmt->bind_param("i", $user_id);
$stmt->execute();
$notes = $stmt->get_result();
?>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Dashboard</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
<script src="js/script.js" defer></script>
</head>
<body>

<?php include 'header.php'; ?>

<div class="main-content" id="main">
  <h2><i class="fa-solid fa-user"></i> My Dashboard</h2>

  <div class="stats-card">
    <p><i class="fa-solid fa-file"></i> You have uploaded <b><?= $notes->num_rows ?></b> notes.</p>
  </div>

  <h3><i class="fa-solid fa-folder-open"></i> My Uploaded Notes</h3>

  <div class="cards-container">
    <?php if ($notes->num_rows > 0): ?>
      <?php while ($row = $notes->fetch_assoc()): ?>
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
            <p><b>Description:</b> <?= htmlspecialchars($row['description']) ?></p>
            <p><i class="fa-solid fa-calendar"></i> <?= date("M d, Y", strtotime($row['uploaded_at'])) ?></p>
          </div>
          <div class="note-actions">
            <a href="<?= htmlspecialchars($row['filepath']) ?>" download class="btn-download">
              <i class="fa-solid fa-download"></i> Download
            </a>
            <a href="dashboard.php?delete=<?= $row['id'] ?>" 
               onclick="return confirm('Are you sure you want to delete this note?');" 
               class="btn-delete">
              <i class="fa-solid fa-trash"></i> Delete
            </a>
          </div>
        </div>
      <?php endwhile; ?>
    <?php else: ?>
      <p>No notes uploaded yet.</p>
    <?php endif; ?>
  </div>
</div>

</body>
</html>
<?php $stmt->close(); ?>
