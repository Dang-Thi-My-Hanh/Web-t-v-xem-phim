package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.movies;

import hcmuaf.nlu.edu.vn.quanlyxemphim.service.MovieService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteMovieController", value = "/delete-movie")
public class DeleteMovieController extends HttpServlet {
    private final MovieService movieService = new MovieService();
    private static final String UPLOAD_DIRECTORY = "users/img"; // Đảm bảo rằng thư mục này nằm trong thư mục gốc của frontend

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String all = request.getParameter("all");

        if (id != null && movieService.deleteMovie(id)) {
            // Nếu có tham số "all", chuyển hướng đến trang "movies-list" với all=true để xem tất cả sản phẩm
            if ("true".equalsIgnoreCase(all)) {
                response.sendRedirect("movies-list?all=true");
            } else {
                // Nếu không có tham số "all", chuyển hướng đến trang "movies-list" với 10 sản phẩm đầu tiên
                response.sendRedirect("movies-list");
            }
        } else {
            // Nếu có lỗi, hiển thị thông báo lỗi và quay lại trang movies.jsp
            request.setAttribute("error", "Xóa sản phẩm thất bại");
            request.getRequestDispatcher("/admin/pages/movies.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}