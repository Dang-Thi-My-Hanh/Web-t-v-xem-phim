package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.account;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "listAccount", value = "/accounts")
public class ListAllUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        String showAll = req.getParameter("showAll");

        //tìm kiếm
        String search = req.getParameter("search");
        String name = req.getParameter("name");

        try {
            List<Users> list = userService.getListUsers();
            if(search!=null){
                list=userService.getListUsersByName(name);
            }
            if (showAll == null) {
                // Hiển thị tối đa 10 mục
                list = list.stream().limit(10).collect(Collectors.toList());
            }
            req.setAttribute("list_user", list);
            req.getRequestDispatcher("/admin/pages/user.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
