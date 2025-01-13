package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.favourite;

import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.FavouriteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addToFavorites")
public class AddToFavoritesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID của bộ phim từ tham số request
        String movieIdStr = request.getParameter("id");

        if (movieIdStr != null) {
            long movieId = Long.parseLong(movieIdStr);

            // Giả sử người dùng đã đăng nhập, bạn sẽ có ID người dùng từ session
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("user");  // Lấy đối tượng User từ session

                            // Gọi hàm thêm vào danh sách yêu thích
                FavouriteService favouriteService = new FavouriteService();
                favouriteService.addToFavorites(movieId, user.getId());

                // Chuyển hướng về trang danh sách yêu thích hoặc thông báo thành công
                response.sendRedirect(request.getContextPath()+"/favorites"); // Hoặc redirect đến trang yêu thích

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID bộ phim không hợp lệ.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
