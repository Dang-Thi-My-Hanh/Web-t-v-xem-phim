package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.account;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.PasswordReset;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.EmailUtilService;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;




import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

@WebServlet(name = "reset-password", value = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    private final UserService userService;
    private final EmailUtilService emailUtil;

    public ResetPasswordController() {
        this.userService = new UserService();
        this.emailUtil = new EmailUtilService();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            switch (action) {
                case "request":
                    sendEmailResetPass(req, resp);
                    break;
                case "reset":
                    resetPass(req, resp);
                    break;
                default:
                    req.setAttribute("error", "Invalid action.");
                    req.getRequestDispatcher("/users/page/reset-password.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại.");
            req.getRequestDispatcher("/users/page/reset-password.jsp").forward(req, resp);
        }
    }

    public void sendEmailResetPass(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // Xử lý yêu cầu gửi email reset mật khẩu
        String email = req.getParameter("email");
        Users user = userService.findUserByEmail(email);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        if (user != null) {
            // Tạo token reset mật khẩu
            String token = userService.generateResetToken();
            req.setAttribute("token", token);

            // Kiểm tra xem token đã tồn tại hay chưa, nếu có thì cập nhật lại
            var resetPassword = userService.findResetTokenByUserId(user.getId());
            if (resetPassword != null) {
                // nếu có tồn tại thì cập nhật
                userService.updateResetTokenForEmail(email, token);
            } else {
                //  Lưu token mới vào cơ sở dữ liệu
                userService.saveResetToken(user.getId(), token);
            }

            // Tạo đường dẫn reset mật khẩu với token
            String resetLink = req.getRequestURL().toString() + "?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8);
            // Sửa lại phần email để đảm bảo đường link có thể nhấn vào
            String emailBody = "<html><body>Nhấp vào link này để xác nhận lấy lại mật khẩu: <a href=\"" + resetLink + "\">Reset mật khẩu</a></body></html>";

            try {
                emailUtil.sendEmailAsync(email, "Lấy lại mật khẩu", emailBody);
                req.setAttribute("error_email", "Email xác thực đã gửi đến bạn");
            } catch (Exception e) {
                req.setAttribute("error_email", "Không thể gửi email, vui lòng thử lại.");
            }

        } else {
            req.setAttribute("error_email", "Email không tồn tại trong hệ thống.");
        }
        req.getRequestDispatcher("/users/page/reset-password.jsp").forward(req, resp);

    }

    public void resetPass(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // Lấy token và mật khẩu từ request
        String token = req.getParameter("token");
        String password = req.getParameter("password");

        if (token == null || token.isEmpty()) {
            req.setAttribute("error_token", "Token không hợp lệ.");
            req.getRequestDispatcher("/users/page/reset-password.jsp").forward(req, resp);
            return;
        }
        // Kiểm tra token trong cơ sở dữ liệu
        PasswordReset reset = userService.findResetTokenByToken(token);
        if (reset != null && reset.getTokenExpire().after(new Timestamp(System.currentTimeMillis()))) {
            // Mã hóa mật khẩu mới
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Cập nhật mật khẩu

            userService.updatePassword(reset.getUserId(), hashedPassword);

            // Vô hiệu hóa token sau khi dùng
            userService.invalidateToken(token);

            // Chuyển hướng đến trang login
            req.getRequestDispatcher( "/users/page/login-signup.jsp").forward(req,resp);
        } else {
            req.setAttribute("verificationRequested", true);
            req.setAttribute("error_token", "Token không hợp lệ hoặc đã hết hạn.");
            req.getRequestDispatcher("/users/page/reset-password.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");

        if (token != null && !token.isEmpty()) {
            // Kiểm tra tính hợp lệ của token trong cơ sở dữ liệu
            PasswordReset reset = null;  // Gọi phương thức tìm token
            try {
                reset = userService.findResetTokenByToken(token);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (reset != null && reset.getTokenExpire().after(new Timestamp(System.currentTimeMillis()))) {
                // Nếu token hợp lệ và chưa hết hạn
                req.setAttribute("verificationRequested", true);  // Thiết lập flag để hiển thị form đặt lại mật khẩu
                req.setAttribute("token", token);  // Truyền token vào form
            } else {
                // Token không hợp lệ hoặc đã hết hạn
                req.setAttribute("error_email", "Token không hợp lệ hoặc đã hết hạn.");
            }
        } else {
            req.setAttribute("error_email", "Token không hợp lệ.");
        }

        // Chuyển hướng đến trang reset-password.jsp để hiển thị form
        req.getRequestDispatcher("/users/page/reset-password.jsp").forward(req, resp);
    }
}







