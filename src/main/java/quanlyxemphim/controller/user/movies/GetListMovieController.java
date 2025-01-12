package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.movies;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.FavouriteService;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.MovieService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "home-page", value = "/home-page")
public class GetListMovieController extends HttpServlet {
    private  final MovieService movieService ;
    public GetListMovieController() {
        movieService = new MovieService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy danh sách tất cả các phim
            List<Movie> movieList = movieService.getAllMovies();
            List<String> posterUrlList = movieService.getListPoster();

            // Kiểm tra xem người dùng đã đăng nhập hay chưa
            HttpSession session = req.getSession();
            Users user = (Users) session.getAttribute("user");

            int favoriteCount = 0; // Mặc định số lượng phim yêu thích = 0
            if (user != null) { // Nếu người dùng đã đăng nhập
                FavouriteService favouriteService = new FavouriteService();
                favoriteCount = favouriteService.getFavoriteCount(user.getId()); // Lấy số lượng phim yêu thích
            }

            // Truyền dữ liệu đến trang JSP
            req.setAttribute("poster", posterUrlList);
            req.setAttribute("movieList", movieList);
            session.setAttribute("favoriteCount", favoriteCount);

            // Chuyển hướng tới trang `home.jsp`
            req.getRequestDispatcher("home.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống! Vui lòng thử lại sau.");
        }
    }

  }
