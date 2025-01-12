package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.movies;

import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.MovieService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddMovieController", value = "/add-movie")
public class AddMovieController extends HttpServlet {
    private final MovieService movieService = new MovieService();
    private static final String UPLOAD_DIRECTORY = "users/img"; // Đảm bảo rằng thư mục này nằm trong thư mục gốc của frontend


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<>(); // Danh sách lưu trữ thông báo lỗi
        String imageUrl = ""; // Biến lưu đường dẫn ảnh
        try {
            // Lấy các tham số từ form
            String title = request.getParameter("title");
            String priceStr = request.getParameter("price");
            String description = request.getParameter("description");
            String genre = request.getParameter("genre");
            String duration = request.getParameter("duration");
            String img = request.getParameter("posterUrl");


            // Kiểm tra và xử lý null hoặc giá trị trống cho từng trường
            if (title == null || title.trim().isEmpty()) {
                errorMessages.add("Tên phim không được để trống!");
            }
            if (priceStr == null || priceStr.trim().isEmpty()) {
                errorMessages.add("Giá vé không được để trống!");
            }

            // Nếu có lỗi, không tiếp tục thêm sản phẩm
            if (!errorMessages.isEmpty()) {
                request.setAttribute("errors", errorMessages);
                request.getRequestDispatcher("/movies-list").forward(request, response);
                return;
            }
            // Chuyển đổi dữ liệu
            double price = Double.parseDouble(priceStr.trim());
            int durationInMinutes = Integer.parseInt(duration.trim());

            // Tạo đối tượng Movie
            Movie movie = new Movie(title, description, genre, img, durationInMinutes, price);
            boolean isAdded = movieService.addMovies(movie);

//             Gửi thông báo thành công hoặc thất bại
            if (isAdded) {
                request.setAttribute("message", "Thêm phim thành công!");
            } else {
                request.setAttribute("message", "Thêm phim không thành công.");
            }
        } catch (NumberFormatException e) {
            errorMessages.add("Dữ liệu nhập không hợp lệ! Vui lòng kiểm tra số lượng, giá, hoặc tỷ lệ giảm giá.");
        } catch (Exception e) {
            errorMessages.add("Lỗi: " + e.getMessage());
        }
        // Hiển thị thông báo lỗi nếu có
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errors", errorMessages);
        }
        // Chuyển hướng về trang sản phẩm
        request.getRequestDispatcher("/movies-list").forward(request, response);
    }


}
