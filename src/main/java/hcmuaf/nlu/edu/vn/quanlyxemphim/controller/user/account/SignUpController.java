package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.account;


import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "signup-user", value = "/signup-user")
public class SignUpController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Kiểm tra đăng ký
            boolean registrationSuccess = userService.registerUser(email, username, password);
            if (registrationSuccess) {
                // Đăng ký thành công, yêu cầu xác thực
                request.setAttribute("verificationRequested", true);
                HttpSession session = request.getSession();
                session.setAttribute("email", email);  // Lưu email vào session
                request.setAttribute("email", email);  // Truyền email đến trang JSP
            } else {
                // Xử lý lỗi (ví dụ: email đã tồn tại)
                request.setAttribute("error_register", "Email hoặc tài khoản đã tồn tại.");

            }
        } catch (Exception e) {
            // Xử lý lỗi khi có lỗi hệ thống
            request.setAttribute("error_register", "Đã xảy ra lỗi, vui lòng thử lại.");

        }

        // Giữ lại trang đăng ký và thông báo lỗi
        request.getRequestDispatcher("users/page/login-signup.jsp").forward(request, response);
    }
}




