package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.account;

import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "updateStatus" , value = "/status-account")
public class UpdateStatusAccountController  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("id");
        String status = req.getParameter("status");

        // Kiểm tra id và status trước khi xử lý
        if (ids == null || ids.trim().isEmpty() || status == null || status.trim().isEmpty()) {
            req.setAttribute("error", "ID hoặc trạng thái không hợp lệ.");
            req.getRequestDispatcher("/accounts").forward(req, resp);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(ids); // Chuyển đổi id sang số nguyên
        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID không phải là số hợp lệ.");
            req.getRequestDispatcher("/accounts").forward(req, resp);
            return;
        }

        UserService userService = new UserService();
        try {
            // Gọi phương thức cập nhật trạng thái
            if (userService.UpdateStatusUser(status, id)) {
                resp.sendRedirect(req.getContextPath() + "/accounts");
            } else {
                req.setAttribute("error", "Cập nhật trạng thái không thành công.");
                req.getRequestDispatcher("/accounts").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            req.getRequestDispatcher("/accounts").forward(req, resp);
        }
    }
    }