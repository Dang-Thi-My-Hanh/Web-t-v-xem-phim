
//------------------------------------------------- Hàm hiển thị chi tiết hóa đơn----------------------------------------------------

// Hàm đóng modal
function closeModal() {
    document.getElementById('orderDetailModal').style.display = "none";
}

// Đóng modal khi click ra ngoài modal
window.onclick = function (event) {
    const modal = document.getElementById('orderDetailModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
// ---------------------------------------------------------Form Edit Information -----------------------------------------------------
// Mở modal
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector('.edit-btn').addEventListener('click', openModal1);
});
function openModal1() {
    console.log("Modal mở");
    // Hiển thị modal
    document.getElementById('editInformationModal').style.display = 'block';

// Lấy dữ liệu từ phần thông tin khách hàng
    const name = document.querySelector('.customer-info p:nth-child(2) strong').nextSibling.nodeValue.trim();
    const phone = document.querySelector('.customer-info p:nth-child(4) strong').nextSibling.nodeValue.trim();
    const address = document.querySelector('.customer-info p:nth-child(5) strong').nextSibling.nodeValue.trim();
// Gán dữ liệu vào các trường input trong modal
    document.getElementById('name').value = name;
    document.getElementById('phone').value = phone;
    document.getElementById('address').value = address;
}

// Đóng modal
function closeModal1() {
    console.log("Modal đóng");
    document.getElementById('editInformationModal').style.display = 'none';
}



