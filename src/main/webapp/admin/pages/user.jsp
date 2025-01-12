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
    <link rel="stylesheet" href="<c:url value="/admin/css/user.css"/>">

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
                <form action="accounts" method="GET">
                    <label>
                        <input type="text" name="name" placeholder="Tìm kiếm ở đây">
                        <ion-icon name="search-outline"><button type="submit" style="border: none; background: none; cursor: pointer;"></button></ion-icon>
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


        <!-------------------- Danh Sách Chi Tiết người dùng------------------ -->
        <div class="details-user">
            <div class="recentOrders">
                <div class="cardHeader">
                    <h2>Danh sách người dùng</h2>
                    <a href="accounts?showAll=true" class="btn">Xem Tất Cả</a>
                </div>
                <div class="update-user">
                    <p style="font-size: 20px; margin-bottom: 5px;">Cập nhật trạng thái người dùng</p>
                    <form action="status-account" method="post">
                    <input type="text" name="id" placeholder="Nhập ID user cần cập nhật"
                           style="font-size: 15px; padding: 3px; border-radius: 5px;">
                    <select title="choice" id="statusSelect" name="status"
                            style="font-size: 15px; border-radius: 5px; padding: 2px;">
                        <option value="Hoạt động">Hoạt động</option>
                        <option value="Không hoạt động">Không hoạt động</option>
                        <option value="Đang chờ xử lý">Đang chờ xử lý</option>
                        <option value="Bị đình chỉ">Bị đình chỉ</option>
                    </select>
                    <button type="submit">Cập nhật trạng thái</button>
                    <c:if test="${not empty error}">
                        <p style="color: red;">${error}</p> <!-- Hiển thị lỗi nếu có -->
                    </c:if>
                    </form>

                </div>
                <div class="list-account-content-button">
                    <button id="add_account">Thêm tài khoản</button>
                </div>
                <table>
                    <thead>
                    <tr>
                        <td>id</td>
                        <td>Tên người dùng</td>
                        <td>Email</td>
                        <td>Số điện thoại</td>
                        <td>Trạng thái</td>
                        <td>Vai trò</td>
                        <td>Hành động</td>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="list_account" items="${list_user}">
                        <tr>
                            <td>${list_account.id}</td>
                            <td>${list_account.username}</td>
                            <td>${list_account.email}</td>
                            <td>${list_account.phoneNumber}</td>
                            <td><span class="statusText">${list_account.status}</span></td>
                            <td>${list_account.role}</td>
                            <td>
                                <div class="v">
                                    <button class="delete-btn" data-id="${list_account.id}">Xóa</button>
                                    </button>
                                </div>
                            </td>

                        </tr>

                    </c:forEach>


                    </tbody>
                </table>
            </div>


        </div>
        <!-- Thêm tài khoản mới -->
        <div id="addAccount" class="modal">
            <div class="modal-content">
                <h3>Thêm tài khoản mới</h3>
                <form id="addAccountForm" action="add-account" method="post">
                    <label for="username">Tên tài khoản:</label>
                    <input type="text" id="username" name="username" required>

                    <label for="password">Mật khẩu:</label>
                    <input type="password" id="password" name="password" required>

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>

                    <label for="role">Quyền:</label>
                    <select id="role" name="role" required>
                        <option value="">Chọn quyền</option>
                        <option value="admin">admin</option>
                        <option value="user">user</option>
                    </select>
                    <c:if test="${not empty error}">
                        <p style="color: red;">${error}</p> <!-- Hiển thị lỗi nếu có -->
                    </c:if>

                    <button id="saveAccount" type="submit">Lưu tài khoản</button>
                    <button type="button" class="close-modal">Thoát</button>
                </form>
            </div>
        </div>


        <!-- Modal Xóa tài khoản -->
        <div id="delete-modal" class="modal">
            <div class="modal-content">
                <h3>Xác nhận xóa</h3>
                <label>Bạn có chắc chắn muốn xóa tài khoản này?</label>
                <div class="button-container">
                    <button id="confirm-delete" class="confirm-delete" >Xóa</button>
                    <button class="close-modal">Hủy</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script src="<c:url value="/admin/js/index.js"/>"></script>
<script src="<c:url value="/admin/js/passwordManagement.js"/>"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Kiểm tra nếu cần hiển thị modal
        const showModal = "${showModal}" === "true";
        if (showModal) {
            const modal = document.getElementById('addAccount');
            modal.style.display = 'block';
        }
    });
</script>


</body>

</html>