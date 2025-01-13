package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "TurnPage", value = "/turn-page")
public class TurnPageUrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("buyingHelp".equals(action)) {
            req.getRequestDispatcher("/users/page/buying-help.jsp").forward(req, resp);
        }

        if("resetPass".equals(action)) {
            req.getRequestDispatcher("/users/page/reset-password.jsp").forward(req, resp);
        }
    }
}
