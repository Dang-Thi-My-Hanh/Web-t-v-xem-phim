package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.movies;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.MovieService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product")
public class ProductController extends HttpServlet {
    private final MovieService movieService;

    public ProductController() {
        this.movieService = new MovieService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movies = null;
        String search = request.getParameter("search");
        String name = request.getParameter("name");
        // Lấy tham số type từ URL (có thể là 'new' hoặc 'hot' hoặc "Action" hoặc "Comedy" hoặc "Romance")
        String type = request.getParameter("type");

        try {
            movies = movieService.getAllMovies();
            if ("hot".equals(type)) {
                movies = movieService.getTop10HighestRevenueMovies();
            } else if ("new".equals(type)) {
                movies = movieService.getNewestMovies();
            } else if ("Action".equals(type)) {
                movies = movieService.getActionMovies();
            } else if ("Comedy".equals(type)) {
                movies = movieService.getComedyMovies();
            } else if ("Romance".equals(type)) {
                movies = movieService.getRomanceMovie();
            }
            // search
            if (search != null && !search.isEmpty()) {
                movies = movieService.getListMoviesByName(name);
            }
            // Truyền danh sách sản phẩm vào request để hiển thị trong JSP
            request.setAttribute("movies", movies);
            request.getRequestDispatcher("users/page/movie.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}