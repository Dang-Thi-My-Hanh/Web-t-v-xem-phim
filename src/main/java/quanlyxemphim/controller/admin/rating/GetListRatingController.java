package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.rating;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Rating;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.RatingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ListRating", value = "/list-rating")
public class GetListRatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RatingService ratingService = new RatingService();

        String showAll = req.getParameter("showAll");
        //tìm kiếm
        String search = req.getParameter("search");
        String movieId = req.getParameter("movieId");

        try{
            List<Rating> listRating = ratingService.getListRating();
            if(search!=null){

                listRating=ratingService.getListRatingByProductId(movieId);
            }
            if (showAll == null) {
                // Hiển thị tối đa 10 mục
                listRating = listRating.stream().limit(10).collect(Collectors.toList());
            }
            req.setAttribute("listRating", listRating);
            req.getRequestDispatcher( "/admin/pages/rating.jsp").forward(req, resp);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
