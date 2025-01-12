// ....
const closeModalBtns = document.querySelectorAll(".close-modal");
// đóng modal
closeModalBtns.forEach(btn => {
    btn.addEventListener("click", () => {
        deleteModal.style.display = "none";  // Đóng modal đúng
        editProductModal.style.display="none";
        addProductModal.style.display="none";
    });
});
// lấy các phần tử
const deleteBtns = document.querySelectorAll("#deleteBtn");
const deleteModal = document.getElementById('deleteModal');
const confirmDeleteBtn = document.getElementById('confirmDelete');
let productIdToDelete = null;  // Biến để lưu id sản phẩm cần xóa
let allParam = null; // Biến để lưu tham số "all"
// Hiển thị modal "Xóa"
deleteBtns.forEach(deleteBtn => {
    deleteBtn.addEventListener('click', (e) => {
        productIdToDelete = e.target.getAttribute('data-product-id');  // Lấy id sản phẩm từ data-product-id
        allParam = e.target.getAttribute("data-all");
        deleteModal.style.display = 'flex';  // Hiển thị modal
    });
});
// xác nhận xóa
confirmDeleteBtn.addEventListener('click', () => {
    if (productIdToDelete) {
        let url = `/quanlyxemphim_war_exploded/delete-movie?id=${productIdToDelete}`;  // Gửi yêu cầu xóa đến controller với id sản phẩm
        if (allParam) {
            url += `&all=${allParam}`;  // Thêm tham số all vào URL nếu có
        }
        window.location.href = url;  // Điều hướng đến URL đã xây dựng
    }
    deleteModal.style.display = 'none';  // Đóng modal sau khi xóa
});

// ----------------------------------------------------------------------------------------------------------
//  Thêm sản phẩm
const addProductbtn = document.getElementById("add-product");
const addProductModal = document.getElementById("addProductModal");

const toast = document.getElementById("toast")
// => hiển thị modal
addProductbtn.addEventListener("click", () => {
    addProductModal.style.display = "flex";
});
const alert = document.querySelector(".alert");
if (alert) {
    alert.style.display = "block";  // Hiển thị thông báo
    setTimeout(() => {
        alert.style.display = "none";  // Ẩn thông báo sau 3 giây
    }, 3000);
}
// ----------------------------------------------------------------------------------------------------------

//Sửa sản phẩm
const editProductbtn = document.getElementById("edit-product");
const editProductModal = document.getElementById("editProductModal");
const saveProductbtn = document.getElementById("save-ProductEdit");
