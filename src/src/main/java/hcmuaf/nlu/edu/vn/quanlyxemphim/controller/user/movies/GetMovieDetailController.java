package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.movies;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Room;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.TimeSlot;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.MovieService;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.RoomService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "product", value = "/movie-detail")
public class GetMovieDetailController extends HttpServlet {
    private  final MovieService movieService ;
    public GetMovieDetailController() {
        movieService = new MovieService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy id của phim từ request
            int id = Integer.parseInt(req.getParameter("id"));

            // Xử lý trạng thái đánh giá
            String ratingStatus = req.getParameter("rating");
            if ("success".equals(ratingStatus)) {
                req.setAttribute("rating", "Đánh giá thành công");
            } else if ("fail".equals(ratingStatus)) {
                req.setAttribute("rating", "Đánh giá thất bại");
            }

            // Lấy thông tin chi tiết của phim
            Movie movie = movieService.getMoviesById(id);
            if (movie == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Phim không tồn tại");
                return;
            }
            req.setAttribute("movie", movie);

            // Lấy danh sách phòng và khung giờ chiếu
            RoomService roomService = new RoomService();
            List<Room> rooms = roomService.getRooms();
            List<TimeSlot> timeSlots = roomService.getTimeSlots();

            // Gán các dữ liệu cần thiết vào request
            req.setAttribute("rooms", rooms);
            req.setAttribute("timeSlots", timeSlots);

            // Forward tới trang hiển thị chi tiết phim
            RequestDispatcher dispatcher = req.getRequestDispatcher("/users/page/movie-detail.jsp");
            dispatcher.forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID phim không hợp lệ");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi xử lý yêu cầu");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}