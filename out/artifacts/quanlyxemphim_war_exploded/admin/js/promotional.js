document.addEventListener('DOMContentLoaded', function () {
    // Lấy tất cả các nút xóa
    const deleteButtons = document.querySelectorAll('.delete-btn');

    // Hàm hiển thị modal
    function showModal(modalId) {
        const modal = document.getElementById(modalId);
        modal.style.display = 'block';
    }


    // Xử lý khi nhấn "Xóa" trong modal
            const confirmDeleteButton = document.querySelector('.confirm-delete');
            document.querySelectorAll('.delete-btn').forEach(function(button) {
                button.addEventListener('click', function(e) {
                    e.preventDefault(); // Ngăn chặn hành động mặc định (như điều hướng lại trang)
                    showModal('delete-modal');
                    const promotionId = this.getAttribute('data-id'); // Lấy ID từ thuộc tính data-id
                    const url = `delete-promotional?id=${promotionId}`; // Đường dẫn xóa tài khoản kèm ID

                    confirmDeleteButton.addEventListener('click', function() {
                        window.location.href = url; // Chuyển hướng đến URL xóa tài khoản
                    });
                });
            });
            // ---------------------------------------------------------Form Edit Information -----------------------------------------------------
            document.querySelectorAll('.edit-btn').forEach(function(button) {
                button.addEventListener('click', function(e) {
                    e.preventDefault(); // Ngăn chặn hành động mặc định
                    showModal('editPromotional');

                    // Lấy ID từ nút bấm
                    const promotionId = this.getAttribute('data-id-update');
                    document.getElementById('promotionId').value = promotionId; // Gán vào trường hidden
                });
            });


    // Đóng tất cả các modal khi nhấn nút đóng
    const closeButtons = document.querySelectorAll('.close-modal');
    closeButtons.forEach(button => {
        button.addEventListener('click', closeModal);
    });
    // Hàm đóng modal
    function closeModal() {
        const modals = document.querySelectorAll('.modal');
        modals.forEach(modal => modal.style.display = 'none');
    }

});

//Thêm sản phẩm
const addPromotions = document.getElementById('add_Promotions');
const addPro = document.getElementById('addPromotionModal')

addPromotions.addEventListener('click', () => {
    addPro.style.display = 'flex';
});