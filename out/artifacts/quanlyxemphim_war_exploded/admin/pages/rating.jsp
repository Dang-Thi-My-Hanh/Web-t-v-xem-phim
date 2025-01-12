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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value="/admin/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/admin/css/review.css"/>">
</head>
<style>
    .add-category {

        margin-bottom: 50px;
        text-align: center;
    }

    .add-category button {
        background-color: #2a2185;
        color: #f1f1f1;
        border-radius: 5px;
        font-size: 30px;
        cursor: pointer;
        padding: 5px;
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
        <!-------------------- Chính --------------------->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <div class="search">
                    <form action="list-rating" method="GET">
                        <label>
                            <input type="text" name="movieId" placeholder="Tìm kiếm ở đây">
                            <ion-icon name="search-outline"><button type="submit" style="border: none; background: none; cursor: pointer;"></button></ion-icon>
                        </label>
                        <input type="hidden" name="search" value="true">
                    </form>
                </div>

                <div class="user">
                    <a href="accounts"> <ion-icon name="person" style="color: #000000; font-size: 25px;"></ion-icon></a>

                </div>
            </div>


            <!-- Phần chính -->
            <div class="review">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Danh sách đánh giá của khách hàng</h2>
                        <a href="list-rating?showAll=true" class="btn">Xem Tất Cả</a>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <td>Mã sản phẩm</td>
                                <td>Mã khách hàng</td>
                                <td>Thông tin đánh giá</td>
                                <td>Ngày đánh giá</td>
                                <td>Hành động</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="rating" items="${listRating}">
                            <tr>
                                <td>${rating.id}</td>
                                <td>${rating.userId}</td>
                                <td>${rating.content}</td>
                                <td>${rating.createdAt}</td>
                                <td class="v">
                                    <button class="delete-btn" data-id="${rating.id}">Xóa</button>
                                </td>
                            </tr>

                            </c:forEach>
                            <!-- Thêm các dòng dữ liệu khác tại đây -->
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- Modal Xóa đánh giá -->
            <div id="delete-modal" class="modal">
                <div class="modal-content">
                    <h3>Xác nhận xóa</h3>
                    <label>Bạn có chắc chắn muốn xóa đánh giá này?</label>
                    <div class="button-container">
                        <button id="confirm-delete" class="confirm-delete">Xóa</button>
                        <button  class="close-modal">Hủy</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script src="<c:url value="/admin/js/index.js"/>"></script>
    <script src ="<c:url value="/admin/js/review.js"/>"></script>

</body>

</html>