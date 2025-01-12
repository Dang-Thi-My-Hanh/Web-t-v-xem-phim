document.addEventListener('DOMContentLoaded', function () {
    // Lấy tất cả các nút chỉnh sửa và xóa
    const editButtons = document.querySelectorAll('.edit-btn');
    const deleteButtons = document.querySelectorAll('.delete-btn');

    // Hàm hiển thị modal
    function showModal(modalId) {
        const modal = document.getElementById(modalId);
        modal.style.display = 'block'; // Hiển thị modal
    }

    // Hàm đóng modal
    function closeModal() {
        const modals = document.querySelectorAll('.modal');
        modals.forEach(modal => modal.style.display = 'none'); // Ẩn tất cả các modal
    }

    // Khi nhấn vào nút chỉnh sửa
    editButtons.forEach(button => {
        button.addEventListener('click', function () {
            const row = button.closest('tr');
            const username = row.children[0].textContent;
            const email = row.children[1].textContent;

            // Điền thông tin vào modal chỉnh sửa
            document.getElementById('edit-username').value = username;
            document.getElementById('edit-email').value = email;

            // Lấy giá trị data-id của nút đã nhấn
            const id = button.getAttribute('data-id');
            console.log("button thứ :" + id); // In ra ID, ví dụ: "1"

            // Hiển thị modal chỉnh sửa
            showModal('edit-modal');
        });
    });

    // Khi nhấn vào nút xóa
    deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
            const row = button.closest('tr');
            const id = button.getAttribute('data-id');
            // Hiển thị modal xóa
            showModal('delete-modal');


        });
    });

    // Đóng tất cả các modal khi nhấn nút đóng
    const closeButtons = document.querySelectorAll('.close-modal');
    closeButtons.forEach(button => {
        button.addEventListener('click', function () {
            closeModal();
        });
    });

    // Hiển thị modal thêm tài khoản
    const addPromotions = document.getElementById('add_account');
    const addPro = document.getElementById('addAccount');
    addPromotions.addEventListener('click', () => {
        addPro.style.display = 'flex';
    });

    let deleteModal = document.getElementById('delete-modal'); // Modal xóa tài khoản
    let confirmDeleteButton = document.getElementById('confirm-delete'); // Nút xác nhận xóa
    document.querySelectorAll('.delete-btn').forEach(function(button) {
        button.addEventListener('click', function(e) {
            e.preventDefault(); // Ngăn chặn hành động mặc định (như điều hướng lại trang)

            const userId = this.getAttribute('data-id'); // Lấy ID từ thuộc tính data-id
            const url = `delete-account?id=${userId}`; // Đường dẫn xóa tài khoản kèm ID

            // Hiển thị modal xác nhận xóa
            deleteModal.style.display = 'block';

            confirmDeleteButton.addEventListener('click', function() {
                window.location.href = url; // Chuyển hướng đến URL xóa tài khoản
            });
        });
    });

    // Đóng modal khi nhấn ra ngoài nội dung modal
    window.addEventListener('click', function (event) {
        const modals = document.querySelectorAll('.modal');
        modals.forEach(modal => {
            if (event.target === modal) {
                modal.style.display = 'none';
            }
        });
    });
});
