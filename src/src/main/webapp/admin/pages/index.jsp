<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bảng điều khiển Quản trị viên </title>
    <!------------------ Kiểu dáng ------------------>
    <link rel="stylesheet" href="<c:url value="/admin/css/style.css"/>">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
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
                    <form action="home" method="get">
                        <label>
                            <input type="text" name="id" placeholder="Tìm kiếm ở đây">
                            <ion-icon name="search-outline"><button type="submit" style="border: none; background: none; cursor: pointer;"></button></ion-icon>
                        </label>
                        <input type="hidden" name="search" value="id">
                    </form>
                </div>

                <div class="user">
                    <a href="passwordManagement.html"> <ion-icon name="person"
                                                                 style="color: #000000; font-size: 25px;"></ion-icon></a>
                </div>
            </div>

            <!------------------- Thẻ------------------>
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers">${totalUsers}</div>
                        <div class="cardName">Số lượng người dùng</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cart-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">${totalRatings}</div>
                        <div class="cardName">Đánh giá</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="chatbubbles-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">${totalSales}₫</div>
                        <div class="cardName">Doanh Thu</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cash-outline"></ion-icon>
                    </div>
                </div>
            </div>

            <!-------------------- Danh Sách Chi Tiết Đơn Hàng------------------ -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Đơn Đặt Gần Đây</h2>
                        <a href="home?showAll=reservations" class="btn">Xem Tất Cả</a>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <td>Mã đơn</td>
                                <td>Tên người đặt</td>
                                <td>Số điện thoại</td>
                                <td>Thanh Toán</td>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reservations" items="${reservationsList}">
                            <tr>
                                <td>${reservations.id}</td>
                                <td>${reservations.customerName}</td>
                                <td>${reservations.customerPhone}</td>
                                <td>${reservations.status}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script src="<c:url value="/admin/js/index.js"/>"></script>
</body>

</html>