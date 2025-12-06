document.addEventListener("DOMContentLoaded", () => {
  const sidebar = document.getElementById("sidebar");
  const main = document.getElementById("main");
  const toggleBtn = document.getElementById("sidebarToggle");

  toggleBtn.addEventListener("click", () => {
    if (window.innerWidth <= 768) {
      sidebar.classList.toggle("active");
      main.classList.toggle("overlay");
    } else {
      sidebar.classList.toggle("collapsed");
      main.classList.toggle("collapsed");
    }
  });

  main.addEventListener("click", () => {
    if (window.innerWidth <= 768 && sidebar.classList.contains("active")) {
      sidebar.classList.remove("active");
      main.classList.remove("overlay");
    }
  });
});
