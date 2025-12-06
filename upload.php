<?php
include 'config.php';
if (!isset($_SESSION['user_id'])) header("Location: index.php");
$user_id = $_SESSION['user_id'];

if (isset($_POST['upload'])) {
    $title = trim($_POST['title']);
    $desc = trim($_POST['description']);
    $file = $_FILES['note'];

    if ($title && $desc && $file['name']) {
        $allowed = ['pdf','docx','jpg','jpeg','png'];
        $ext = strtolower(pathinfo($file['name'], PATHINFO_EXTENSION));

        if (in_array($ext, $allowed)) {
            $targetDir = "uploads/";
            if (!is_dir($targetDir)) mkdir($targetDir, 0755, true);

            $filename = time().'_'.preg_replace('/[^a-zA-Z0-9._-]/', '_', $file['name']);
            $path = $targetDir.$filename;

            if (move_uploaded_file($file['tmp_name'], $path)) {
                // Prepared statement to insert into DB
                $stmt = $conn->prepare("INSERT INTO notes (user_id, title, description, filename, filepath) VALUES (?, ?, ?, ?, ?)");
                $stmt->bind_param("issss", $user_id, $title, $desc, $filename, $path);
                $stmt->execute();
                $stmt->close();

                echo "<script>alert('Note uploaded successfully!'); window.location='dashboard.php';</script>";
            } else {
                echo "<script>alert('Failed to upload file.');</script>";
            }
        } else {
            echo "<script>alert('Invalid file type. Only PDF, DOCX, and images are allowed.');</script>";
        }
    } else {
        echo "<script>alert('All fields are required!');</script>";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Upload Note</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body>


<!-- Toggle Button (always visible) -->
<button id="sidebarToggle" class="toggle-btn">
  <i class="fa-solid fa-bars"></i>
</button>

<!-- Sidebar -->
<div class="sidebar" id="sidebar">
  <div class="sidebar-header">
    <h2><i class="fa-solid fa-book"></i> <span>Notes Share</span></h2>
  </div>
  <ul>
    <li><a href="dashboard.php" class="active"><i class="fa-solid fa-user"></i> <span>My Notes</span></a></li>
    <li><a href="upload.php"><i class="fa-solid fa-upload"></i> <span>Upload Note</span></a></li>
    <li><a href="all_notes.php"><i class="fa-solid fa-globe"></i> <span>All Notes</span></a></li>
    <li><a href="logout.php"><i class="fa-solid fa-right-from-bracket"></i> <span>Logout</span></a></li>
  </ul>
</div>

<div class="main-content" id="main">
  <h2><i class="fa-solid fa-upload"></i> Upload Your Note</h2>

  <form method="POST" enctype="multipart/form-data" class="upload-form">
    <label>Title:</label>
    <input type="text" name="title" placeholder="Enter note title" required>

    <label>Description:</label>
    <textarea name="description" placeholder="Enter short description..." rows="3" required></textarea>

    <label>Select File (PDF, DOCX, Image):</label>
    <input type="file" name="note" required accept=".pdf,.docx,.jpg,.jpeg,.png">

    <button type="submit" name="upload" class="btn-primary">
      <i class="fa-solid fa-cloud-arrow-up"></i> Upload
    </button>
  </form>
</div>
</body>
</html>
