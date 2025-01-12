package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.account;


import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "reset-code", value = "/reset-code")
public class ResetCodeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserService();
        HttpSession session = request.getSession();
        // Kiểm tra email trong session
        String email = (String) session.getAttribute("email");
        if (email != null) {
            try {
                //Gọi phương thức gửi lại mã code
                userService.sendCode(email);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // Đăng ký thành công, yêu cầu xác thực
            request.setAttribute("verificationRequested", true);
            request.setAttribute("error_code", "Mã xác thực mới đã được gửi đến email của bạn.");
        } else {
            request.setAttribute("error_code", "Không tìm thấy email để gửi mã xác thực.");
        }
        // Trả về trang đăng ký
        request.getRequestDispatcher("users/page/login-signup.jsp").forward(request, response);
    }
    }

