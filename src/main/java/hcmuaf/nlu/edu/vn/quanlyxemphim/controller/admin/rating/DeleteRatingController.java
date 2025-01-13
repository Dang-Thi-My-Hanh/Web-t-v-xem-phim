package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.rating;


import hcmuaf.nlu.edu.vn.quanlyxemphim.service.RatingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteRating", value = "/delete-rating")
public class DeleteRatingController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        RatingService ratingService = new RatingService();
        try {
            if (ratingService.deleteRating(id)) {
                resp.sendRedirect(req.getContextPath()+"/list-rating");
            } else {
                req.setAttribute("error", "Xóa đánh giá thất bại.");
                req.getRequestDispatcher("/list-rating").forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
