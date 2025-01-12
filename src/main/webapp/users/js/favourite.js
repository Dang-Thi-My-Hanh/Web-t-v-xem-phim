document.addEventListener('DOMContentLoaded', function () {
    const cartItemsContainer = document.querySelector('.cart-items-container');

    if (!cartItemsContainer) {
        console.error('Không tìm thấy container cho giỏ hàng.');
        return;
    }

    // Thao tác với nút cộng/trừ
    cartItemsContainer.addEventListener('click', function (event) {
        const target = event.target;

        // Xử lý khi nhấn vào nút cộng hoặc trừ
        if (target.classList.contains('plus-btn') || target.classList.contains('minus-btn')) {
            const cartItem = target.closest('.cart-item');
            const qtyInput = cartItem.querySelector('.qty-input');

            if (!qtyInput) {
                console.error('Không tìm thấy input số lượng.');
                return;
            }

            let qty = parseInt(qtyInput.value);

            if (target.classList.contains('plus-btn')) {
                qty++;
            } else if (target.classList.contains('minus-btn') && qty > 1) {
                qty--;
            }

            qtyInput.value = qty; // Cập nhật lại giá trị số lượng
        }
    });



    // Áp dụng mã giảm giá
    const applyBtn = document.getElementById('apply-btn');
    const voucherInput = document.getElementById('voucher');
    const voucherError = document.getElementById('voucher-error');
    const totalElement = document.getElementById('total');

    applyBtn.addEventListener('click', function () {
        const voucherCode = voucherInput.value.trim();
        const validVoucher = "QWERTYUI";
        const discountAmount = 10000; // Giảm 10.000 ₫

        if (voucherCode === validVoucher) {
            voucherError.textContent = "Bạn được giảm 10.000 ₫";
            voucherError.style.color = 'green';
            voucherError.style.display = 'block';

            let total = parseFloat(totalElement.textContent.replace('₫', '').replace(/\./g, ''));
            total -= discountAmount;
            totalElement.textContent = total.toLocaleString('vi-VN') + ' ₫';
        } else {
            voucherError.textContent = "Mã giảm giá không hợp lệ!";
            voucherError.style.color = 'red';
            voucherError.style.display = 'block';
        }
    });
});
