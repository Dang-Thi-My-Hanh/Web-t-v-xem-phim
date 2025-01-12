


const productsPerPage = 12; // Số sản phẩm trên mỗi trang
let currentPage = 1;

function displayProducts(page) {
    const products = document.querySelectorAll("#product-list .list");
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

