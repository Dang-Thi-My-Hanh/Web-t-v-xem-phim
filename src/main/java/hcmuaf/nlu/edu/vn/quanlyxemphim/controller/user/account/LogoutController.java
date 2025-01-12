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

@WebServlet(name = "logout", value = "/logout")
public class LogoutController extends HttpServlet {

    private final UserService userService;
    public LogoutController() {
        this.userService = new UserService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

          try{
            HttpSession session = req.getSession();
            Users user = (Users) session.getAttribute("user");
            if (user == null) {
                // Nếu user không tồn
                req.getRequestDispatcher( "/users/page/login-signup.jsp").forward(req, resp);
                return;
            }
            // Cập nhật trạng thái người dùng thành "Không hoạt động"
            userService.UpdateStatusUser("Không hoạt động", user.getId());

            // Hủy session (đăng xuất)
            req.getSession().invalidate();
            // Chuyển hướng tới trang đăng nhập
            req.getRequestDispatcher( "/users/page/login-signup.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu POST nếu cần
        doGet(request, response); // Gọi lại doGet nếu logic giống nhau
    }
}
