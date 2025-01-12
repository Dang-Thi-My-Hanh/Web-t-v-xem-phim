package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.movies;

import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.MovieService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "GetMovieController", value = "/movies-list")
public class GetMovieController extends HttpServlet {
    private final MovieService movieService;

    public GetMovieController() {
        this.movieService = new MovieService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy tham số "all" từ request
        String showAll = request.getParameter("showAll");
        String search = request.getParameter("search");
        String name = request.getParameter("name");
        try {
            List<Movie> movies = movieService.getAllMovies();
            if (search != null && name != null) {
                // Tìm kiếm sản phẩm theo tên
                movies = movieService.getListMoviesByName(name);
            }
            if (showAll == null) {
                // Hiển thị tối đa 10 mục
                movies = movies.stream().limit(10).collect(Collectors.toList());
            }
            // Mở modal
            String action = request.getParameter("action");
            if ("show".equals(action)) {
                request.setAttribute("showModal", true);
            }
            // Truyền danh sách sản phẩm vào request để hiển thị trong JSP
            request.setAttribute("movies", movies);
            request.getRequestDispatcher("/admin/pages/movies.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}