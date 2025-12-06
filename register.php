<?php include 'config.php'; ?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register - Notes Sharing</title>
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body class="auth-body">

<div class="auth-container">
  <div class="auth-box">
    <h2><i class="fa-solid fa-book"></i> Notes Sharing</h2>
    <h3>Create Account</h3>

    <form method="POST" class="auth-form">
      <div class="input-group">
        <i class="fa-solid fa-user"></i>
        <input type="text" name="username" placeholder="Username" required>
      </div>

      <div class="input-group">
        <i class="fa-solid fa-envelope"></i>
        <input type="email" name="email" placeholder="Email" required>
      </div>

      <div class="input-group">
        <i class="fa-solid fa-lock"></i>
        <input type="password" name="password" placeholder="Password" required>
      </div>

      <button type="submit" name="register" class="btn-primary">
        <i class="fa-solid fa-user-plus"></i> Register
      </button>

      <p class="auth-footer">
        Already have an account?
        <a href="index.php">Login</a>
      </p>
    </form>
  </div>
</div>

</body>
</html>

<?php
if (isset($_POST['register'])) {
    $username = trim($_POST['username']);
    $email = trim($_POST['email']);
    $password = password_hash($_POST['password'], PASSWORD_DEFAULT);

    // Check for duplicate username/email using prepared statement
    $stmt = $conn->prepare("SELECT id FROM users WHERE username = ? OR email = ?");
    $stmt->bind_param("ss", $username, $email);
    $stmt->execute();
    $stmt->store_result();

    if ($stmt->num_rows > 0) {
        echo "<script>alert('Username or Email already exists!');</script>";
    } else {
        // Insert new user securely
        $stmt_insert = $conn->prepare("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
        $stmt_insert->bind_param("sss", $username, $email, $password);
        if ($stmt_insert->execute()) {
            echo "<script>alert('Registered Successfully! Please login.'); window.location='index.php';</script>";
        } else {
            echo "<script>alert('Error: ".$conn->error."');</script>";
        }
        $stmt_insert->close();
    }
    $stmt->close();
}
?>
