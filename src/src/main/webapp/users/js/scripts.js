document.addEventListener("DOMContentLoaded", function () {
    const backToTopButton = document.getElementById("backToTop");

        // Lấy các phần tử
    const menuToggle = document.querySelector('.menu-toggle');
    const navMenu = document.querySelector('.nav');

    // Hiển thị nút khi cuộn xuống
    window.onscroll = function () {
        if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
            backToTopButton.style.display = "block";
        } else {
            backToTopButton.style.display = "none";
        }
    };

    // Quay về đầu trang khi nhấn nút
    backToTopButton.onclick = function () {
        window.scrollTo({ top: 0, behavior: "smooth" });
    };

    // Xử lý sự kiện click vào nút menu
    menuToggle.addEventListener('click', () => {
    navMenu.classList.toggle('active'); // Thêm hoặc xóa lớp "active"
  });
});