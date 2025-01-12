<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bảng điều khiển Quản trị viên </title>
    <!------------------ Kiểu dáng ------------------>
    <link rel="stylesheet" href="<c:url value="/admin/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/admin/css/products.css"/>">

</head>

<style>
    /* Định dạng chung cho thông báo lỗi */
    .alert-error {
        background-color: #f44336; /* Màu đỏ để dễ nhận diện lỗi */
        color: white; /* Màu chữ trắng */
        padding: 20px; /* Khoảng cách bên trong */
        border-radius: 8px; /* Bo góc mềm mại */
        margin: 15px 0; /* Khoảng cách giữa các thông báo lỗi */
        font-family: 'Arial', sans-serif; /* Phông chữ dễ đọc */
        font-size: 16px; /* Kích thước chữ */
        position: fixed; /* Đặt vị trí cố định trên màn hình */
        top: 50%; /* Căn giữa theo chiều dọc */
        left: 50%; /* Căn giữa theo chiều ngang */
        transform: translate(-50%, -50%); /* Di chuyển lại 1 nửa chiều rộng và chiều cao để hoàn toàn căn giữa */
        z-index: 1000; /* Đảm bảo thông báo luôn nổi bật trên các phần tử khác */
        width: 80%; /* Chiều rộng của thông báo */
        max-width: 500px; /* Chiều rộng tối đa */
        text-align: center; /* Căn giữa nội dung */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Bóng đổ nhẹ cho thông báo */
        transition: all 0.3s ease; /* Hiệu ứng chuyển động mượt mà */
    }

    /* Kiểu danh sách lỗi */
    .alert-error ul {
        list-style-type: none; /* Loại bỏ dấu chấm đầu dòng */
        padding: 0;
        margin: 0;
    }

    /* Kiểu cho mỗi mục lỗi */
    .alert-error ul li {
        margin-bottom: 10px; /* Khoảng cách giữa các lỗi */
        padding: 10px; /* Khoảng cách trong mỗi lỗi */
        border: 1px solid #ff0000; /* Viền đỏ quanh mỗi lỗi để làm nổi bật */
        border-radius: 4px; /* Bo góc của các mục lỗi */
        background-color: #d32f2f; /* Nền hơi sáng hơn một chút */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Bóng đổ cho mỗi mục lỗi */
        text-align: left; /* Căn trái cho các thông báo lỗi */
        font-weight: bold; /* In đậm văn bản */
    }

    /* Nút đóng thông báo */
    .alert-error .close {
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 20px;
        color: white;
        background-color: transparent;
        border: none;
        cursor: pointer;
        transition: transform 0.2s ease;
    }

    /* Hiệu ứng khi di chuột vào nút đóng */
    .alert-error .close:hover {
        transform: rotate(90deg); /* Xoay nút khi di chuột vào */
    }

    /* Ẩn thông báo khi có class hidden */
    .alert-error.hidden {
        display: none; /* Khi có class hidden, ẩn thông báo */
    }

    /* Thông báo thành công */
    .alert-success {
        background-color: #4CAF50; /* Màu xanh lá cây */
        color: white;
        padding: 20px;
        border-radius: 8px;
        margin: 15px 0;
        font-family: 'Arial', sans-serif;
        font-size: 16px;
        position: fixed;
        top: 50%;
        left: 60%;
        transform: translate(-50%, -50%);
        z-index: 1000;
        width: 80%;
        max-width: 500px;
        text-align: center;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
    }

    /* Thông báo lỗi */
    .alert-error {
        background-color: #f44336; /* Màu đỏ */
        color: white;
        padding: 20px;
        border-radius: 8px;
        margin: 15px 0;
        font-family: 'Arial', sans-serif;
        font-size: 16px;
        position: fixed;
        top: 50%;
        left: 60%;
        transform: translate(-50%, -50%);
        z-index: 1000;
        width: 80%;
        max-width: 500px;
        text-align: center;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
    }

    /* Nút đóng */
    .alert .close {
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 20px;
        color: white;
        background-color: transparent;
        border: none;
        cursor: pointer;
        transition: transform 0.2s ease;
    }

    /* Hiệu ứng khi di chuột vào nút đóng */
    .alert .close:hover {
        transform: rotate(90deg);
    }

    .scroll-to-top {
        position: fixed;
        bottom: 20px;
        right: 20px;
        display: none; /* Ẩn nút ban đầu */
        background-color: #15283e;
        color: white;
        border: none;
        border-radius: 50%;
        padding: 10px 15px;
        font-size: 18px;
        cursor: pointer;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.3);
        z-index: 1000;
        transition: all 0.3s ease;
    }

    .scroll-to-top:hover {
        background-color: #82a0c2;
        transform: scale(1.1);
    }


</style>
<body>
<!-- ------------------ Điều hướng -------------------->
<div class="container">
    <div class="navigation">
        <ul>
            <li>
                <a href="home">
                        <span class="icon">
                           <img src="https://png.pngtree.com/png-vector/20220611/ourmid/pngtree-cinema-reel-icon-simple-vector-png-image_4849780.png" alt="">
                        </span>
                    <span class="title">Movie</span>
                </a>
            </li>

            <li>
                <a href="home">
                        <span class="icon">
                            <ion-icon name="home-outline"></ion-icon>
                        </span>
                    <span class="title">Trang chủ</span>
                </a>
            </li>

            <li>
                <a href="accounts">
                        <span class="icon">
                            <ion-icon name="people-outline"></ion-icon>
                        </span>
                    <span class="title">Khách Hàng</span>
                </a>
            </li>

            <li>
                <a href="movies-list">
                        <span class="icon">
                            <ion-icon name="cube-outline"></ion-icon>
                        </span>
                    <span class="title">Sản phẩm</span>
                </a>
            </li>
            <li>
                <a href="reservationsLists">
                        <span class="icon">
                            <ion-icon name="receipt-outline"></ion-icon>
                        </span>
                    <span class="title">Hóa đơn</span>
                </a>
            </li>
            <li>
                <a href="list-rating">
                        <span class="icon">
                            <ion-icon name="chatbubble-outline"></ion-icon>
                        </span>
                    <span class="title">Đánh giá</span>
                </a>
            </li>

            <li>
                <a href="logout">
                        <span class="icon">
                            <ion-icon name="log-out-outline"></ion-icon>
                        </span>
                    <span class="title" onclick="lockout()">Đăng Xuất</span>
                </a>
            </li>
        </ul>
    </div>
    <!------------------- Chính ------------------>
    <div class="main">
        <div class="topbar">
            <div class="toggle">
                <ion-icon name="menu-outline"></ion-icon>
            </div>
            <div class="search">
                <form action="products-list" method="GET">
                    <label>
                        <input type="text" name="name" placeholder="Tìm kiếm ở đây">
                        <ion-icon name="search-outline">
                            <button type="submit" style="border: none; background: none; cursor: pointer;"></button>
                        </ion-icon>
                    </label>
                    <input type="hidden" name="search" value="true">
                </form>
            </div>

            <div class="user">
                <a href="accounts">
                    <ion-icon name="person"
                              style="color: #000000; font-size: 25px;"></ion-icon>
                </a>

            </div>
        </div>

        <!-- Phần chính -->
        <div class="products">
            <div class="recentOrders">
                <div class="cardHeader">
                    <h2>Danh sách phim</h2>
                    <a href="movies-list?showAll=true" class="btn">Xem Tất Cả</a>
                </div>
                <div class="list-products-content-button">
                    <button id="add-product">Thêm phim</button>
                </div>
                <!-- Table products -->
                <table>
                    <thead>
                    <tr>
                        <th>Hình ảnh</th>
                        <th>Mã phim</th>
                        <th>Tên phim</th>
                        <th>Giá vé</th>
                        <th>Ngày tạo</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Các sản phẩm sẽ được thêm vào đây -->
                    <c:forEach var="movie" items="${movies}">
                        <tr>
                            <td><img src="${movie.posterUrl}" alt="${movie.title}"></td>
                            <td>${movie.id}</td>
                            <td>${movie.title}</td>
                            <td><fmt:formatNumber value="${movie.ticketPrice}" type="number"
                                                  groupingUsed="true"/>₫
                            </td>
                            <td><fmt:formatDate value="${movie.createdAt}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                            <td class="v">
                                <button type="button"><a style="text-decoration: none; color:black"
                                                         href="/quanlyxemphim_war_exploded/edit-movie?id=${movie.id}">Sửa</a>
                                </button>
                                <button id="deleteBtn" data-product-id="${movie.id}" data-all="${param.all}">Xóa
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <button id="scrollToTop" class="scroll-to-top">⬆</button>
                </table>

                <!-- Kiểm tra và hiển thị tất cả thông báo lỗi -->
                <c:if test="${not empty errors}">
                    <div class="alert alert-error">
                        <ul>
                            <c:forEach var="error" items="${errors}">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                        <span class="close" onclick="this.parentElement.style.display='none';">&times;</span>
                    </div>
                </c:if>

                <!-- Kiểm tra và hiển thị thông báo thành công hoặc thất bại -->
                <c:if test="${not empty message}">
                    <div class="alert alert-success">
                        <span>${message}</span>
                    </div>
                </c:if>

                <!-- Mẫu điền thêm bộ phim -->
                <div id="addProductModal" class="modal">
                    <div class="modal-content">
                        <h3>Thêm phim mới</h3>
                        <form id="addProductForm" action="add-movie" method="post">
                            <label for="movieName">Tên phim:</label>
                            <input type="text" id="movieName" name="title" placeholder="Nhập tên phim">

                            <label for="moviePrice">Giá vé:</label>
                            <input type="text" id="moviePrice" name="price" placeholder="Nhập giá vé">

                            <label for="movieGenre">Thể loại:</label>
                            <input type="text" id="movieGenre" name="genre" placeholder="Nhập số thể loại">

                            <div class="modal-field">
                                <label for="duration">Thời lượng phim:</label>
                                <input type="text" id="duration" name="duration" placeholder="Nhập thời lượng phim">
                            </div>

                            <div class="modal-field">
                                <label for="description">Mô tả:</label>
                                <textarea id="description" name="description" rows="3"
                                          placeholder="Nhập mô tả phim"></textarea>
                            </div>

                            <label for="posterUrl">Hình ảnh:</label>
                            <input type="text" id="posterUrl" name="posterUrl">

                            <button type="submit" id="save-Product">Lưu phim</button>
                            <button type="button" class="close-modal" onclick="closeModal()">Thoát</button>
                        </form>
                    </div>
                </div>

                <!-- Modal sửa thông tin sản phẩm -->
                <div id="editProductModal" class="modal">
                    <div class="modal-content">
                        <h3>Sửa thông tin phim</h3>
                        <form id="editProductForm" action="${pageContext.request.contextPath}/edit-movie" method="post">
                            <label for="movieNameEdit">Tên phim:</label>
                            <input type="text" id="movieNameEdit" name="name" value="${movie.title}"
                                   placeholder="Nhập tên phim" required>

                            <label for="moviePrice">Giá vé:</label>
                            <input type="text" id="moviePriceEdit" name="price" value="${movie.ticketPrice}"
                                   placeholder="Nhập giá vé" required>

                            <label for="movieGenre">Thể loại:</label>
                            <input type="text" id="movieGenreEdit" name="genre" placeholder="Nhập thể loại"
                                   value="${movie.genre}" required>

                            <div class="modal-field">
                                <label for="duration">Thời lượng phim:</label>
                                <input type="text" id="durationEdit" name="duration" placeholder="Nhập thời lượng phim"
                                       value="${movie.duration}">
                            </div>
                            <div class="modal-field">
                                <label for="description">Mô tả:</label>
                                <textarea id="descriptionEdit" name="description" rows="3"
                                          placeholder="Nhập mô tả phim">${movie.description}</textarea>
                            </div>

                            <label for="posterUrl">Hình ảnh:</label>
                            <input type="text" id="posterUrlEdit" name="posterUrl" value="${movie.posterUrl}"/>
                            <input type="hidden" name="currentImageUrl" value="${movie.posterUrl}"/>
                            <input type="hidden" name="id" value="${movie.id}"/>
                            <button type="submit" id="save-ProductEdit">Lưu thay đổi</button>
                            <button type="button" class="close-modal">Thoát</button>
                        </form>
                    </div>
                </div>

                <!-- Modal xác nhận xóa -->
                <div id="deleteModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3>Xác nhận xóa</h3>
                        </div>
                        <p>Bạn chắc chắn muốn xóa sản phẩm này?</p>
                        <div class="modal-actions">
                            <button id="confirmDelete" class="confirm-delete">Xóa</button>
                            <button class="close-modal">Hủy</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>

    <script src="<c:url value="/admin/js/index.js"/>"></script>
    <script src="<c:url value="/admin/js/products.js"/>"></script>
    <script>// Lấy tham chiếu đến nút
    const scrollToTopButton = document.getElementById('scrollToTop');

    // Hiển thị nút khi cuộn xuống quá 200px
    window.addEventListener('scroll', () => {
        if (window.scrollY > 200) {
            scrollToTopButton.style.display = 'block'; // Hiển thị nút
        } else {
            scrollToTopButton.style.display = 'none'; // Ẩn nút
        }
    });

    // Xử lý sự kiện click vào nút để quay lại đầu trang
    scrollToTopButton.addEventListener('click', () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth' // Cuộn mượt
        });
    });
    </script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            // Kiểm tra nếu cần hiển thị modal
            const showModal = "${showModal}" === "true";
            if (showModal) {
                const modal = document.getElementById('editProductModal');
                modal.style.display = 'flex';

            }
        });
    </script>
</div>
</body>
</html>