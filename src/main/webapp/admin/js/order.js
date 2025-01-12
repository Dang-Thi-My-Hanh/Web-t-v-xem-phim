document.addEventListener('DOMContentLoaded', function () {
    // Lấy tất cả các nút chi tiết và xóa
    const viewDetailButtons = document.querySelectorAll('.view-detail-btn');
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

    viewDetailButtons.forEach(button => {
        button.addEventListener('click', function () {
            // Hiển thị modal chỉnh sửa
            showModal('orderDetailModal');
        });
    });

    //  Khi nhấn vào nút xóa
    const confirmDeleteButton = document.querySelector('.confirm-delete');
    document.querySelectorAll('.delete-btn').forEach(function(button) {
        button.addEventListener('click', function(e) {
            e.preventDefault(); // Ngăn chặn hành động mặc định (như điều hướng lại trang)
            showModal('delete-modal');
            const reservationsId = this.getAttribute('data-id'); // Lấy ID từ thuộc tính data-id
            const url = `reservationsLists?id=${reservationsId}&action=remove`; // Đường dẫn xóa tài khoản kèm ID

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
