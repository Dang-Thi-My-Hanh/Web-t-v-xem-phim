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
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("login".equals(action)) {
            req.getRequestDispatcher("/users/page/login-signup.jsp").forward(req, resp);
            return;
        }

        if("reset".equals(action)) {
            resp.sendRedirect(req.getContextPath() + "/reset-password.jsp");
            return;
        }
        // đăng nhập
        UserService userService = new UserService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            if (userService.login(username, password)) {
                Users user = userService.getUser(username);

                 if( user != null && user.getIsEmailVerified() == 1) {
                     if("Bị đình chỉ".equals(user.getStatus())) {
                         req.setAttribute("error_login", "Tài khoản đã bị cấm");
                         req.getRequestDispatcher("/users/page/login-signup.jsp").forward(req, resp);
                         return;
                     }
                    // lưu session
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);

                    //cập nhật trạng thái
                    userService.UpdateStatusUser("Hoạt động", user.getId());

                    // Quay vể trang gần nhất
                     String redirectUrl = (String) session.getAttribute("redirectUrl");
                     if (redirectUrl != null) {
                         session.removeAttribute("redirectUrl"); // Xóa redirectUrl khỏi session
                         resp.sendRedirect(redirectUrl); // Quay về URL trước đó
                     }else
                         if ("admin".equals(user.getRole())) {
                             resp.sendRedirect(req.getContextPath() + "/home");
                         } else if ("user".equals(user.getRole())) {
                             resp.sendRedirect(req.getContextPath() + "/home-page");
                         } else {
                             req.setAttribute("error_login", "Không tìm thấy người dùng");
                             req.getRequestDispatcher("/users/page/login-signup.jsp").forward(req, resp);

                     }
                } else {
                    req.setAttribute("error_login", "Tài khoản chưa được xác thực, hoặc không tồn tại");
                    req.getRequestDispatcher( "/users/page/login-signup.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error_login", "Tài khoản hoặc mật khẩu không chính xác");
                req.getRequestDispatcher( "/users/page/login-signup.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("error_login", "Lỗi hệ thống: " + e.getMessage());
            req.getRequestDispatcher("/users/page/login-signup.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}