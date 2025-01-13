function initializeSlider(containerClass, nextBtnClass, prevBtnClass, itemsClass) {
    const rightBtn = document.querySelector(nextBtnClass);
    const leftBtn = document.querySelector(prevBtnClass);
    const items = document.querySelectorAll(itemsClass);
    const container = document.querySelector(containerClass);
    let index = 0;

    rightBtn.addEventListener("click", function () {
        index = index + 1;
        if (index > items.length - 1) {
            index = 0; // Quay lại đầu
        }
        updateSlider();
    });

    leftBtn.addEventListener("click", function () {
        index = index - 1;
        if (index < 0) {
            index = items.length - 1; // Quay lại cuối
        }
        updateSlider();
    });

    function updateSlider() {
        const offset = -index * 100; // Tính toán offset
        container.style.transform = `translateX(${offset}%)`;
        console.log(`Current Index in ${containerClass}:`, index); // Kiểm tra giá trị index
    }
}

    // Khởi tạo các slider
    initializeSlider(".slider-product-one-content-items-content", ".nut_phai-1", ".nut_trai-1", ".slider-product-one-content-items");
    initializeSlider(".slider-product-two-content-items-content", ".nut_phai-2", ".nut_trai-2", ".slider-product-two-content-items");
    initializeSlider(".slider-product-three-content-items-content", ".nut_phai-3", ".nut_trai-3", ".slider-product-three-content-items");
    initializeSlider(".slider-product-four-content-items-content", ".nut_phai-4", ".nut_trai-4", ".slider-product-four-content-items");
    initializeSlider(".slider-product-five-content-items-content", ".nut_phai-5", ".nut_trai-5", ".slider-product-five-content-items");
    initializeSlider(".slider-product-six-content-items-content", ".nut_phai-6", ".nut_trai-6", ".slider-product-six-content-items");
    initializeSlider(".slider-product-seven-content-items-content", ".nut_phai-7", ".nut_trai-7", ".slider-product-seven-content-items");
    initializeSlider(".slider-product-eight-content-items-content", ".nut_phai-8", ".nut_trai-8", ".slider-product-eight-content-items");
    initializeSlider(".slider-product-nine-content-items-content", ".nut_phai-9", ".nut_trai-9", ".slider-product-nine-content-items");
