<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vật Liệu Xây Dựng TQH </title>
</head>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4-beta3/css/all.min.css"/>
<link rel="stylesheet" href="<c:url value="/users/css/informationCustomer.css"/>">
<link rel="stylesheet" href="<c:url value="/users/css/home.css"/>">
<style>
    /* Định kiểu cho các thông báo */
    .alert {
        position: fixed; /* Để thông báo hiển thị cố định trên màn hình */
        top: 50%; /* Đặt thông báo vào giữa màn hình theo chiều dọc */
        left: 50%; /* Đặt thông báo vào giữa màn hình theo chiều ngang */
        transform: translate(-50%, -50%); /* Dịch chuyển lại thông báo để nó chính giữa */
        padding: 20px; /* Đệm để thông báo không dính vào mép */
        text-align: center; /* Căn giữa nội dung trong thông báo */
        border-radius: 15px;
        font-size: 20px; /* Cỡ chữ lớn hơn */
        font-weight: bold;
        z-index: 9999; /* Đảm bảo thông báo luôn hiển thị phía trên */
        animation: fadeIn 0.5s ease-in-out;
        opacity: 1;
    }

    /* Thông báo thành công */
    .alert-success {
        background-color: #d4edda;
        color: #155724;
        border: 1px solid #c3e6cb;
    }

    /* Thông báo thất bại */
    .alert-danger {
        background-color: #f8d7da;
        color: #721c24;
        border: 1px solid #f5c6cb;
    }

    /* Thông báo thông tin */
    .alert-info {
        background-color: #f7d774;
        color: #000000;
        border: 1px solid #bee5eb;
    }

    /* Thông báo cảnh báo */
    .alert-warning {
        background-color: #fff3cd;
        color: #856404;
        border: 1px solid #ffeeba;
    }

    /* Animation cho thông báo khi xuất hiện */
    @keyframes fadeIn {
        0% {
            opacity: 0;
        }
        100% {
            opacity: 1;
        }
    }

    /* Animation cho thông báo khi biến mất */
    @keyframes fadeOut {
        0% {
            opacity: 1;
        }
        100% {
            opacity: 0;
            display: none;
        }
    }

    /* Sử dụng animation fade-out sau 3 giây */
    .fade-out {
        animation: fadeOut 1s ease-in-out forwards;
    }
</style>
<body>
<div id="section-header1">
    <div class="container">
        <div class="banner">
            <div class="top-left">
                <p class="animated-text">
                    <marquee>Chào mừng bạn đến với website của chúng tôi !</marquee>
                </p>
            </div>
            <div class="top-right">
         <span id="user-greeting" style="display: none; color: #ffffff;">
                           Xin chào,  <span
                 id="username">${sessionScope.user.username != null ? sessionScope.user.username : ''}</span>!</span>

                <form action="informationCustomer" method="get">
                    <button type="submit" class="account-link" id="signup-link"
                            style="display: none;">
                        <i class="fas fa-user-circle"></i> Tài khoản
                    </button>
                </form>
                <form action="login" method="post">
                    <input name="action" type="hidden" value="login"/>
                    <button type="submit" id="login-link">
                        <span><i class="fa fa-fw fa-user"></i> Đăng Nhập</span>
                    </button>
                </form>
                <form action="logout" method="post">
                    <button type="submit" id="logout-link"
                            style="display: none;"><span>Đăng Xuất</span></button>
                </form>
            </div>
        </div>

    </div>
</div>
<div class="fixed">
    <div id="section-header2">
        <div class="container">
            <div class="menu">
                <!-- Logo bên trái -->
                <div class="logo">
                    <a href="home-page"><img src="https://png.pngtree.com/png-vector/20220611/ourmid/pngtree-cinema-reel-icon-simple-vector-png-image_4849780.png" alt="Logo"></a>
                </div>

                <!-- Thanh tìm kiếm ở giữa -->
                <form action="product" method="GET">
                    <div class="search-bar">
                        <input type="hidden" name="search" value="true">
                        <input name="name" type="text" placeholder="Tìm kiếm tên phim...">
                        <button type="submit" title="icon"><i class="fa fa-fw fa-search"></i></button>
                    </div>
                </form>

                <!-- Thông tin bên phải -->
                <div class="info">
                    <div class="hotline">
                        <span class="hotline-text">HOTLINE LIÊN HỆ </span>
                        <a href=""> 0905.090.252</a>
                    </div>
                </div>
                <div class="cart">
                    <div class="cart-wrapper">
                        <!-- Giỏ hàng -->
                        <a href="favorites" style="color: #ff0000">
                            <i class="fas fa-heart"></i>
                        </a>
                        <span class="cart-count" id="cart-count">${sessionScope.favoriteCount}</span>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div id="section-nav">
        <div class="container">
            <button class="menu-toggle"><i class="fa-solid fa-bars"></i> Menu</button>
            <ul class="nav navbar-nav navbar-center">
                <li class="propClone">
                    <span class="none-a"><i class="fa-solid fa-list"
                                            style="color: #d0cdcd;"></i> &nbsp;&nbsp;THỂ LOẠI</span>
                    <ul class="items">
                        <li><a href="product?type=Action">&nbsp;&nbsp;<i
                                class="fa-solid fa-chevron-right"></i>&nbsp;&nbsp;HÀNH ĐỘNG</a></li>
                        <li><a href="product?type=Comedy">&nbsp;&nbsp;<i
                                class="fa-solid fa-chevron-right"></i>&nbsp;&nbsp;HÀI</a></li>
                        <li><a href="product?type=Romance">&nbsp;&nbsp;<i
                                class="fa-solid fa-chevron-right"></i>&nbsp;&nbsp;TÌNH CẢM</a></li>
                    </ul>
                </li>
                <li class="propClone"><a href="home-page"><i class="fa-solid fa-house"></i>&nbsp;&nbsp; TRANG CHỦ </a>
                </li>

                <li class="propClone">
                    <a href="product?type=new">
                        <i class="fa-solid fa-film"></i>&nbsp;&nbsp;PHIM MỚI
                    </a>
                </li>
                <li class="propClone">
                    <a href="product?type=hot">
                        <i class="fa-solid fa-fire"></i>&nbsp;&nbsp;PHIM HOT
                    </a>
                </li>
                <li class="propClone">
                    <span class="none-a"> <i class="fa-solid fa-book"></i> &nbsp;&nbsp; HƯỚNG DẪN </span>
                    <ul class="items">
                        <li><a href="turn-page?action=buyingHelp">&nbsp;&nbsp;<i
                                class="fa-solid fa-chevron-right"></i>&nbsp;&nbsp;HƯỚNG DẪN ĐẶT PHIM</a>

                    </ul>
                </li>

            </ul>

        </div>
    </div>
</div>

<div id="section-content-1">
    <div class="account-info-page">
        <h1>Thông tin tài khoản</h1>
        <p class="greeting">Xin chào, ${sessionScope.user.username != null ? sessionScope.user.username : ''}</p>
        <%-- Kiểm tra xem có thông báo nào không --%>
        <c:if test="${not empty message}">
            <div class="alert alert-info">
                    ${message}
            </div>
        </c:if>
        <div class="content">
           <!-- Thông tin khách hàng -->
            <div class="customer-info">
                <h2>Thông tin khách hàng</h2>
                <p><strong>Tên:</strong>${info.fullName}</p>
                <p><strong>Email:</strong> ${info.email}</p>
                <p><strong>Số điện thoại:</strong>${info.phoneNumber}</p>
                <p><strong>Địa chỉ:</strong> ${info.address}</p>
                <button class="edit-btn" onclick="openModal1()">Chỉnh sửa thông tin</button>
            </div>
        </div>

    </div>

    <!-- Modal Hủy đơn hàng -->
    <div id="cancelOrderModal" class="modal">
        <div class="modal-content">
            <h2 class="modal-title">Xác nhận hủy đơn hàng</h2>
            <p class="titles">Bạn chắc chắn muốn hủy đơn hàng này?</p>
            <div class="modal-buttons-dele">
                <form id="cancelOrderForm" method="GET" action="delete-order-infor">
                    <input type="hidden" id="orderId" name="orderId" value="">
                    <button type="submit" class="confirm-delete" id="confirmCancelOrderBtn">Hủy đơn hàng</button>
                </form>
                <button type="button" class="close-modal-dele" id="closeModalBtn">Đóng</button>
            </div>
        </div>
    </div>



    <!-- Modal cho chỉnh sửa thông tin  -->
    <div id="editInformationModal" class="modal">
        <div class="modal-content">
            <span class="close-btn " onclick="closeModal1()" &times;></span>
            <h2 class="modal-title">Chỉnh sửa thông tin</h2>

            <div class="editInformationContent">
                <!-- Form chỉnh sửa thông tin -->
                <form id="editInfoForm" action="update-info" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Tên</label>
                        <input type="text" name="fullName" class="form-control" id="name" placeholder="Nhập tên của bạn"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Số điện thoại</label>
                        <input type="tel" name="phoneNumber" class="form-control" id="phone"
                               placeholder="Nhập số điện thoại" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Địa chỉ</label>
                        <textarea class="form-control" name="address" id="address" rows="3" placeholder="Nhập địa chỉ"
                                  required></textarea>
                    </div>
                    <!-- Thông báo lỗi -->
                    <c:if test="${not empty error}">
                        <p style="color: red;">${error}</p> <!-- Hiển thị lỗi nếu có -->
                    </c:if>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" id="saveChanges">Lưu thay đổi</button>
                        <button type="button" class="btn btn-secondary" onclick="closeModal1()">Hủy</button>
                    </div>
                </form>
            </div>


        </div>
    </div>
</div>

</div>

<div id="section-footer">
    <div class="container">
        <div class="contact-info">
            <h3>Quản Lý Phim TQH</h3>
            <p>Địa chỉ: Trường ĐH Nông Lâm TP. HCM</p>
            <p>Điện thoại: 0314 597 812</p>
            <p>Hotline: 0905 090 252</p>
            <p>Email: support@quanlyphim.com</p>
        </div>
        <div class="links">
            <h3>Liên kết</h3>
            <ul>
                <li><a href="turn-page?action=introduce">Giới thiệu</a></li>
                <li><a href="turn-page?action=termAndService">Điều khoản và dịch vụ</a></li>
                <li><a href="turn-page?action=moviesList">Danh sách phim</a></li>
                <li><a href="turn-page?action=contactUs">Liên hệ</a></li>
            </ul>
        </div>
        <div class="social-media">
            <h3>Mạng xã hội</h3>
            <ul>
                <li><a href="https://www.facebook.com/quanlyphim"><i
                        class="fa-brands fa-facebook" style="color: #d1d1d1;"></i></a></li>
                <li><a href="https://www.instagram.com/quanlyphim/"><i class="fa-brands fa-instagram-square"
                                                                       style="color: #d1d1d1;"></i></a></li>
                <li><a href="https://twitter.com/quanlyphim"><i class="fa-brands fa-twitter"
                                                                style="color: #d1d1d1;"></i></a></li>
            </ul>
        </div>
        <div class="copyright">
            <p>© 2024 Quản Lý Phim TQH: Tất cả quyền được bảo lưu.</p>
        </div>
    </div>

    <div>
        <li style="list-style-type: none;">
            <a href="tel:0314597812" target="_blank" rel="noopener"><i class="fa-solid fa-phone call"
                                                                       style="color: #02bc15d1;"></i></a>
        </li>
    </div>
    <div>
        <li style="list-style-type: none;">
            <a href="https://www.instagram.com/quanlyphim/" target="_blank" rel="noopener"><i class="fab fa-instagram icon"
                                                                                              style="color: #f12020;"></i></a>
        </li>
    </div>
    <div id="fb">
        <li style="list-style-type: none;">
            <a href="https://www.facebook.com/quanlyphim" target="_blank" rel="noopener"><i
                    class="fa-brands fa-facebook-square icon" style="color: #0911ff;"></i></a>
        </li>
    </div>
    <button id="backToTop" title="Quay về đầu trang">⬆</button>
</div>
<script src="<c:url value="/users/js/scripts.js"/>"></script>
<script src="<c:url value="/users/js/login-signup.js"/>"></script>
<script src="<c:url value="/users/js/informationCustomer.js"/>"></script>
<script>
    // mở modal
    document.addEventListener("DOMContentLoaded", function () {
        // Kiểm tra nếu cần hiển thị modal
        const showModal = "${showModal}" === "true";
        if (showModal) {
            const modal = document.getElementById('orderDetailModal');
            modal.style.display = 'block';
        }
    });
    // thông báo tắt trong 3s
    window.addEventListener('DOMContentLoaded', function () {
        const alert = document.querySelector('.alert');
        if (alert) {
            setTimeout(function () {
                alert.classList.add('fade-out');
            }, 3000);  // 3 giây trước khi bắt đầu hiệu ứng biến mất
        }
    });

</script>

</body>
</html>