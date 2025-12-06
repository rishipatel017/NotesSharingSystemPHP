<?php include 'config.php'; ?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login - Notes Sharing</title>
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body class="auth-body">

<div class="auth-container">
  <div class="auth-box">
    <h2><i class="fa-solid fa-book"></i> Notes Sharing</h2>
    <h3>Login</h3>

    <form method="POST" class="auth-form">
      <div class="input-group">
        <i class="fa-solid fa-user"></i>
        <input type="text" name="username" placeholder="Username" required>
      </div>

      <div class="input-group">
        <i class="fa-solid fa-lock"></i>
        <input type="password" name="password" placeholder="Password" required>
      </div>

      <button type="submit" name="login" class="btn-primary">
        <i class="fa-solid fa-right-to-bracket"></i> Login
      </button>

      <p class="auth-footer">Don’t have an account?
        <a href="register.php">Register</a>
      </p>
    </form>
  </div>
</div>

</body>
</html>

<?php
if (isset($_POST['login'])) {
  $username = $_POST['username'];
  $password = $_POST['password'];
  $sql = "SELECT * FROM users WHERE username='$username'";
  $result = $conn->query($sql);
  if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    if (password_verify($password, $row['password'])) {
      $_SESSION['user_id'] = $row['id'];
      header("Location: dashboard.php");
      exit;
    } else {
      echo "<script>alert('Invalid Password');</script>";
    }
  } else {
    echo "<script>alert('User not found');</script>";
  }
}
?>
