package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.movies;


import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.MovieService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "EditMovieController ", value = "/edit-movie")
public class EditMovieController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID sản phẩm cần sửa
        MovieService movieService = new MovieService();
        try {
            int movieId = Integer.parseInt(request.getParameter("id"));
            Movie movie = movieService.getMoviesById(movieId);
            // Gửi sản phẩm tới JSP
            request.setAttribute("movie", movie);

            request.getRequestDispatcher("/movies-list?action=show").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnect dbConnect = new DBConnect();
        try {
            int movieId = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("name");
            String priceStr = request.getParameter("price");
            String description = request.getParameter("description");
            String genre = request.getParameter("genre");
            String duration = request.getParameter("duration");
            String img = request.getParameter("posterUrl");
            // Chuyển đổi dữ liệu
            double price = Double.parseDouble(priceStr.trim());
            int durationInMinutes = Integer.parseInt(duration.trim());

            // Kiểm tra và gán giá trị mặc định cho các trường nếu là null hoặc rỗng
            if (description == null || description.trim().isEmpty()) {
                description = "";
            }
            title = (title == null || title.trim().isEmpty()) ? "Default Title" : title;

            // Kiểm tra nếu không có hình ảnh mới, giữ lại hình ảnh cũ
            if (img == null || img.trim().isEmpty()) {
                img = request.getParameter("currentImageUrl");  // Giữ lại hình ảnh cũ
            }
            Movie upMovie = new Movie(movieId,title, description, genre, img, durationInMinutes, price);
            MovieService movieService = new MovieService();
            try {
                // Cập nhật sản phẩm với hình ảnh mới (hoặc cũ nếu không có hình ảnh mới)
                upMovie.setPosterUrl(img);
                // Cập nhật sản phẩm
                boolean isUpdated = movieService.updateMovies(movieId, upMovie);
                System.out.println("Update movie status: " + isUpdated);
                if (isUpdated) {
                    response.sendRedirect(request.getContextPath() + "/movies-list");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/movies-list");
            }
        } finally {
            dbConnect.closeConnection();
        }
    }
}