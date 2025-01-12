


const productsPerPage = 12; // Số sản phẩm trên mỗi trang
let currentPage = 1;

function displayProducts(page) {
    const products = document.querySelectorAll("#product-list .name-cart");
    const totalPages = Math.ceil(products.length / productsPerPage);
    
    // Giới hạn trang trong khoảng hợp lệ
    page = Math.max(1, Math.min(page, totalPages));
    currentPage = page;

    // Ẩn tất cả sản phẩm trước khi hiển thị sản phẩm của trang hiện tại
    products.forEach((product, index) => {
        product.style.display = "none";
        if (index >= (page - 1) * productsPerPage && index < page * productsPerPage) {
            product.style.display = "block";
        }
    });

    // Cập nhật thông tin số trang
    document.getElementById("page-info").textContent = `Trang ${page} của ${totalPages}`;
    document.getElementById("prev").disabled = (page === 1);
    document.getElementById("next").disabled = (page === totalPages);
}

function changePage(offset) {
    displayProducts(currentPage + offset);
}

// Hiển thị trang đầu tiên khi tải trang
displayProducts(currentPage);



//  Giuwx trạng thái cảu các item

  // Tìm tất cả các mục trong danh mục
const items = document.querySelectorAll('.left-sidebar .item');

// Đọc trạng thái từ localStorage và đánh dấu mục đang được chọn
const selectedCategory = localStorage.getItem('selectedCategory');
if (selectedCategory) {
    document.querySelector(`[data-category="${selectedCategory}"]`).classList.add('active');
}

// Xử lý khi nhấn vào một mục
items.forEach(item => {
    item.addEventListener('click', function(event) {
        // Ngăn chặn hành động mặc định của link
        event.preventDefault();

        // Xóa lớp "active" khỏi tất cả các mục
        items.forEach(el => el.classList.remove('active'));

        // Thêm lớp "active" vào mục hiện tại
        item.classList.add('active');

        // Lưu danh mục đã chọn vào localStorage
        const category = item.getAttribute('data-category');
        localStorage.setItem('selectedCategory', category);

        // Lấy đường dẫn từ thẻ <a> và chuyển đến trang đó
        const link = item.querySelector('a').getAttribute('href');
        window.location.href = link;
    });
});

// Xóa trạng thái khi nhấn ra ngoài (vào bất kỳ vị trí nào khác)
document.addEventListener('click', function(event) {
    const isClickInside = event.target.closest('.item');
    if (!isClickInside) {
        // Xóa trạng thái trong localStorage
        localStorage.removeItem('selectedCategory');

        // Xóa lớp "active" khỏi tất cả các mục
        items.forEach(el => el.classList.remove('active'));
    }
});

