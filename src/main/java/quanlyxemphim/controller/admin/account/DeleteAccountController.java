package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.account;


import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteAccount", value = "/delete-account")
public class DeleteAccountController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        int id = Integer.parseInt(req.getParameter("id"));
        if(userService.deleteAccount(id)){
            resp.sendRedirect(req.getContextPath()+"/accounts");
        }else{
            req.setAttribute("error", "Xóa tài khoản thất bại.");
            req.getRequestDispatcher("/accounts").forward(req, resp);
        }
    }
}
