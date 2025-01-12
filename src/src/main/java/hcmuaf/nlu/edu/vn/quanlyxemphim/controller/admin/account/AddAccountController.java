package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.account;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "addAccount" , value = "/add-account")
public class AddAccountController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");


        UserService userService = new UserService();
        try {
            if(userService.addAccount(username, password, email,role)) {
                Users user = userService.getUser(username);
                userService.verifyEmail(email);
                userService.UpdateStatusUser("Đang chờ xử lý", user.getId());
                resp.sendRedirect(req.getContextPath()+"/accounts");
            } else {
            req.setAttribute("error", "Email hoặc tài khoản đã tồn tại.");
            req.setAttribute("showModal", true); // Thêm thuộc tính hiển thị modal
            req.getRequestDispatcher("/accounts").forward(req, resp); // Hiển thị lại form với thông báo lỗi
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
