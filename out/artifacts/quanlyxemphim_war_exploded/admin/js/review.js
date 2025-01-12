document.addEventListener('DOMContentLoaded', function () {
    // Lấy tất cả các nút xóa
    const deleteButtons = document.querySelectorAll('.delete-btn');

    // Hàm hiển thị modal
    function showModal(modalId) {
        const modal = document.getElementById(modalId);
        modal.style.display = 'block';
    }

    // Hàm đóng modal
    function closeModal() {
        const modals = document.querySelectorAll('.modal');
        modals.forEach(modal => modal.style.display = 'none');
    }

    let deleteModal = document.getElementById('delete-modal'); // Modal xóa tài khoản
    let confirmDeleteButton = document.getElementById('confirm-delete'); // Nút xác nhận xóa
    document.querySelectorAll('.delete-btn').forEach(function(button) {
        button.addEventListener('click', function(e) {
            e.preventDefault(); // Ngăn chặn hành động mặc định (như điều hướng lại trang)

            const ratingId = this.getAttribute('data-id'); // Lấy ID từ thuộc tính data-id
            const url = `delete-rating?id=${ratingId}`; // Đường dẫn xóa tài khoản kèm ID

            // Hiển thị modal xác nhận xóa
            deleteModal.style.display = 'block';

            confirmDeleteButton.addEventListener('click', function() {
                window.location.href = url; // Chuyển hướng đến URL xóa tài khoản
            });
        });
    });


    // Đóng tất cả các modal khi nhấn nút đóng
    const closeButtons = document.querySelectorAll('.close-modal');
    closeButtons.forEach(button => {
        button.addEventListener('click', closeModal);
    });
});
