package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.account;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "updateInfo" , value = "/update-info")
public class UpdateInfoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        UserService userService = new UserService();
        try {
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("user");
            int id = user.getId(); // Lấy ID từ session

            String fullName = request.getParameter("fullName");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");

            // Tạo đối tượng Users và gán dữ liệu từ form
            Users updatedUser = new Users(fullName, phoneNumber, address);
            if (userService.setUpdateInfoUser(id, updatedUser)) {    // Gọi service để cập nhật thông tin người dùng
                // Chuyển hướng về trang thông báo hoặc danh sách người dùng
                response.sendRedirect(request.getContextPath() + "/informationCustomer");
            } else {
                request.setAttribute("error", "có lỗi xảy ra, vui lòng thử lại!");
                request.setAttribute("showModal", true); // Thêm thuộc tính hiển thị modal
                request.getRequestDispatcher("/informationCustomer").forward(request, response); // Hiển thị lại form với thông báo lỗi
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }}