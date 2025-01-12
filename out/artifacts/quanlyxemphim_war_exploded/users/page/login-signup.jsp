<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vật Liệu Xây Dựng TQH </title>
</head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
      crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4-beta3/css/all.min.css"/>
    <link rel="stylesheet" href="<c:url value="/users/css/home.css"/>">
    <link rel="stylesheet" href="<c:url value="/users/css/login-signup.css"/>">

<!-- CSS để hiển thị Modal -->
<style>
    .modal {
        display: block;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.5);
        z-index: 1000;
    }

    .modal-content {
        background: white;
        padding: 20px;
        border-radius: 10px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 80%;
        max-width: 400px;
    }

    .close-button {
        background: none;
        border: none;
        font-size: 20px;
        cursor: pointer;
        position: absolute;
        top: 10px;
        right: 10px;
    }

    .verification-code-inputs input {
        width: 30px;
        height: 40px;
        margin: 5px;
        text-align: center;
    }
</style>
<body>
<div id="section-header1">
    <div class="container">
        <div class="top-left">
            <p class="animated-text">
                <marquee>Chào mừng bạn đến với website của chúng tôi !</marquee>
            </p>
        </div>
        <div class="top-right">
            <form action="login" method="post">
                <input name="action" type="hidden" value="login" />
                <button type="submit" id="login-link">
                    <span><i class="fa fa-fw fa-user"></i> Đăng Nhập</span>
                </button>
            </form>

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
                        <span class="cart-count" id="cart-count">${sessionScope.cartItemCount}</span>
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


<!-- Content-->
<div class="content">
    <div class="container" id="container">


            <div class="form-container sign-up">
                    <form id="register-form" action="signup-user" method="post">
                    <h1>Tạo tài khoản</h1>
                    <div class="social-icons">
                        <a href="" class="icon-logo google"><i class="fa-brands fa-google-plus-g g"></i></a>
                        <a href="#" class="icon-logo facebook"><i class="fa-brands fa-facebook-f f"></i></a>
                    </div>
                    <span>Hoặc sử dụng email để đăng kí tài khoản</span>

                    <!-- Nhập Email -->
                    <input type="email" name="email" id="register-email" placeholder="Email" value="${email}" >
                    <div id="email-error" style="color: red; font-size: 10px;"></div>

                    <!-- Nhập Username -->
                    <input type="text" name="username" id="register-username" placeholder="UserName" >
                    <div id="username-error" style="color: red; font-size: 10px;"></div>

                    <!-- Nhập Password -->
                    <input type="password" name="password" id="register-password" placeholder="Password" >
                    <div id="password-error" style="color: red; font-size: 10px;"></div>
                    <div id="error-server" style="color: red; font-size: 10px;">
                        <c:if test="${not empty error_register}">
                            ${error_register}
                        </c:if>
                    </div>
                    <!-- Nút đăng ký -->
                    <button type="submit" id="submit-register">Đăng kí</button>
                </form>
             </div>



        <div class="form-container sign-in none">
            <form id="login-form" action="login" method="post">
                <h1>Đăng nhập</h1>
                <div class="social-icons">
                    <a href="#" class="icon-logo"><i class="fa-brands fa-google-plus-g g"></i></a>
                    <a href="#" class="icon-logo"><i class="fa-brands fa-facebook-f f"></i></a>
                </div>
                <span>Hoặc sử dụng tài khoản đã đăng kí</span>
                <input type="text" name="username" id="login-username" placeholder="UserName">
                <span id="username-errorlog" style="color: red; font-size: 10px;" class="error-message"></span> <!-- Thông báo lỗi tên đăng nhập -->
                <input type="password" name="password" id="login-password" placeholder="Password">
                <span id="password-errorlog" style="color: red; font-size: 10px;" class="error-message"></span> <!-- Thông báo lỗi mật khẩu -->
                <div id="error-login" style="color: red; font-size: 10px;">
                    <c:if test="${not empty error_login}">
                        ${error_login}
                    </c:if>
                </div>

                <a class="blue" href="turn-page?action=resetPass">Quên mật khẩu?</a>
                <button type="submit" id="login-button">Đăng nhập</button>
            </form>
        </div>
        <div class="toggle-container none">
            <div class="toggle">
                <div class="toggle-panel toggle-left">
                    <h1>Chào mừng, trở lại!</h1>
                    <p>Nhập thông tin cá nhân của bạn để sử dụng tất cả các tính năng của trang web</p>
                    <button class="hidden" id="login">Đăng nhập</button>
                    <button class="hidden" id="home"><a href="home-page" style="text-decoration: none ;color: #FFFFFF">Trang chủ</a></button>
                </div>
                <div class="toggle-panel toggle-right">
                    <h1>Chào, bạn!</h1>
                    <p>Đăng ký với thông tin cá nhân của bạn để sử dụng tất cả các tính năng của trang web</p>
                    <button class="hidden" id="register">Đăng kí</button>
                    <button class="hidden" id="HOME"><a href="home-page" style="text-decoration: none ;color: #FFFFFF" >Trang chủ</a></button>
                </div>
            </div>
        </div>


        <!-- Form xác thực email -->
        <div class="overlay" id="overlay"></div>
        <c:if test="${not empty verificationRequested}">
            <!-- Modal xác thực email -->
            <div id="verification-modal" class="modal">
                <div class="modal-content">
                    <button title="close" type="button" class="close-button" id="close-button">
                        <i class="fas fa-times"></i> <!-- Font Awesome icon for "X" -->
                    </button>

                    <h1>Xác thực email</h1>

                    <!-- Form xác thực email -->
                    <form id="verification-form" action="verify-email" method="post">
                        <div class="verification-code-inputs " style="display: flex; gap: 10px;">
                            <input class="verification-code" type="text" name="code1" maxlength="1">
                            <input class="verification-code" type="text" name="code2" maxlength="1">
                            <input class="verification-code" type="text" name="code3" maxlength="1">
                            <input class="verification-code" type="text" name="code4" maxlength="1">

                        </div>
                        <!-- Thông báo lỗi -->
                        <div id="error-message" class="error-message">
                            <c:if test="${not empty error_code}">
                                ${error_code}
                            </c:if>
                        </div>
                        <button type="submit" id="verify-button">Xác thực</button>
                    </form>

                    <!-- Form gửi lại mã -->
                    <form id="reset-password-form" action="reset-code" method="post">
                        <button type="submit" class="reset">Gửi lại</button>
                    </form>
                </div>
            </div>
        </c:if>
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
                <li><a href="https://www.facebook.com/profile.php?id=100044411504061"><i
                        class="fa-brands fa-facebook" style="color: #d1d1d1;"></i></a></li>
                <li><a href="https://www.instagram.com/paq.2012/"><i class="fa-brands fa-instagram-square"
                                                                     style="color: #d1d1d1;"></i></a></li>
                <li><a href="https://x.com/?lang=vi"><i class="fa-brands fa-twitter"
                                                        style="color: #d1d1d1;"></i></a></li>
            </ul>
        </div>
        <div class="copyright">
            <p>© 2024 Quản Lý Phim TQH: Tất cả quyền được bảo lưu.</p>
        </div>
    </div>
    <div>
        <li style="list-style-type: none;"><a href="https://zaloweb.com" target="_blank" rel="noopener"><i
                class="fa-solid fa-phone call" style="color: #02bc15d1;"></i></a></li>
    </div>
    <div>
        <li style="list-style-type: none;"><a href="https://www.instagram.com" target="_blank"
                                              rel="noopener"><i class="fab fa-instagram icon"
                                                                style="color: #f12020;"></i></a></li>
    </div>
    <div id="fb">
        <li style="list-style-type: none;"><a href="https://www.facebook.com"
                                              target="_blank" rel="noopener"><i
                class="fa-brands fa-facebook-square icon"
                style="color: #0911ff;"></i></a></li>
    </div>
    <button id="backToTop" title="Quay về đầu trang">⬆</button>
</div>

<script src="${pageContext.request.contextPath}/users/js/login-signup.js"></script>
<script src="${pageContext.request.contextPath}/users/js/scripts.js"></script>

</body>

</html>