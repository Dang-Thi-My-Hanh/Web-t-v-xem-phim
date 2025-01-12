package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.account;


import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "verify-email", value = "/verify-email")
public class VerifyEmailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        // Lấy mã xác thực từ các trường nhập liệu
        String code1 = request.getParameter("code1");
        String code2 = request.getParameter("code2");
        String code3 = request.getParameter("code3");
        String code4 = request.getParameter("code4");

        // Kết hợp các mã thành một chuỗi
        String code = code1 + code2 + code3 + code4;
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        try {
            if (userService.isCode(email, code)) {
                if (userService.verifyEmail(email)) {
                    //xóa thông tin xác thực
                    userService.deleteVerifyCode(email);
                    // Chuyển hướng đến trang đăng nhập hoặc trang yêu cầu sau khi xác thực thành công
                    request.getRequestDispatcher( "users/page/login-signup.jsp").forward(request,response); // Chuyển hướng tới trang đăng nhập
                    return; // Thoát khỏi phương thức ngay sau khi chuyển hướng
                }
            } else {
                request.setAttribute("verificationRequested", true);
                request.setAttribute("error_code", "Mã xác thực không hợp lệ hoặc đã hết hạn.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error_code", "Đã xảy ra lỗi, vui lòng thử lại.");
        }
        // Giữ lại form ký và thông báo lỗi
        request.getRequestDispatcher("/users/page/login-signup.jsp").forward(request, response);

    }



}




