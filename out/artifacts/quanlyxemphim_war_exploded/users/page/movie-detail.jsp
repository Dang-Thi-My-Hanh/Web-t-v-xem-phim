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

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4-beta3/css/all.min.css"/>
<link rel="stylesheet" href="<c:url value="/users/css/home.css"/>">
<link rel="stylesheet" href="<c:url value="/users/css/product-detail.css"/>">


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

                <form action="informationCustomer" method="post">
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
                    <a href="home-page"><img
                            src="https://png.pngtree.com/png-vector/20220611/ourmid/pngtree-cinema-reel-icon-simple-vector-png-image_4849780.png"
                            alt="Logo"></a>
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
    <div class="product-details">
        <!-- Hình ảnh sản phẩm -->
        <div class="product-image">
            <img src="${movie.posterUrl}" alt="${movie.title}"/>
            <div class="review-movie">
                <div class="review">
                    <h2>Đánh giá</h2>
                    <form id="commentform" class="comment-form" action="create-rating" method="post">
                        <input type="hidden" name="movieId" value="${movie.id}">
                        <input type="hidden" name="userId" value="${sessionScope.user.id}">
                        </input>
                        <textarea name="content" rows="3" placeholder="Ý kiến của bạn..." required></textarea>
                        <c:if test="${not empty rating}">
                            <p style="color: red;">${rating}</p> <!-- Hiển thị lỗi nếu có -->
                        </c:if>
                        <button type="submit">Gửi</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Thông tin sản phẩm -->

        <div class="product-info">
            <h2 id="title-movie" class="movie-title">Tên Phim: ${movie.title}</h2>
            <p class="movie-description">
                ${movie.description}
            </p>
            <p><strong>Thể loại: </strong>${movie.genre}</p>
            <p><strong>Thời lượng: </strong>${movie.duration} phút</p>
            <p><strong>Ngày phát hành: </strong><fmt:formatDate value="${movie.releaseDate}" pattern="dd/MM/yyyy"/></p>
        </div>
    </div>
</div>


<div id="section-content-2">
    <div class="products-similar">
        <div class="list-similar">
            <h3>Đặt vé và chọn khung giờ</h3>
            <c:forEach var="room" items="${rooms}">
                <div class="room-info">
                    <h4>Phòng chiếu: ${room.name}</h4>
                    <div class="time-slots">
                        <h5>Khung giờ chiếu:</h5>
                        <c:forEach var="timeSlot" items="${timeSlots}">
                            <c:if test="${timeSlot.roomId == room.id}">
                                <a
                                   href="reservation?roomId=${room.id}&timeSlotId=${timeSlot.id}&movieId=${movie.id}&startTime=${timeSlot.startTime}">
                                    <button style="cursor: pointer; background-color: #d7ff00;border-radius: 10px;">${timeSlot.startTime}</button>
                                </a>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
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


<script src="<c:url value="/users/js/login-signup.js"/>"></script>
<script src="<c:url value="/users/js/scripts.js"/>" defer></script>
</body>

</html>