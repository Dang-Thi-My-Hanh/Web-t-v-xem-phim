package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.home;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Reservations;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.HomeService;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.ReservationService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "HomeController", value = "/home")
public class HomeController extends HttpServlet {
    private final HomeService homeService = new HomeService();
    private final ReservationService reservationService = new ReservationService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        String showAll = request.getParameter("showAll");
        String id = request.getParameter("id");
        try {

            // Gọi phương thức để lấy tất cả đơn hàng từ service
            List<Reservations> reservationsList = null;
            try {
                reservationsList = reservationService.getAllReservation();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // Đặt danh sách đơn hàng vào request attribute để chuyển đến JSP
            request.setAttribute("reservationsList", reservationsList);

            // Lấy các dữ liệu từ HomeService
            int totalUsers = homeService.totalAccount();
            int totalRatings = homeService.totalRating();
            double totalSales = homeService.totalSale();
            if (search != null) {
                try {
                    int userId = Integer.parseInt(id);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            // Hiển thị tất cả dữ liệu theo yêu cầu
            if (showAll != null) {
                if (showAll.equals("reservations")) {
                    request.setAttribute("reservations", reservationsList);// Hiển thị tất cả đơn hàng
                }
                if (showAll.equals("users")) {
                    reservationsList = reservationsList.stream().limit(10).collect(Collectors.toList());
                    request.setAttribute("reservations", reservationsList);
                }
            } else {
                // Mặc định hiển thị tối đa 10 mục
                reservationsList = reservationsList.stream().limit(10).collect(Collectors.toList());
                request.setAttribute("reservations", reservationsList);
            }
            // Đặt các giá trị vào request để chuyển đến JSP
            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalRatings", totalRatings);
            request.setAttribute("totalSales", totalSales);

            // Forward dữ liệu đến trang JSP để hiển thị
            request.getRequestDispatcher("/admin/pages/index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Lỗi hiển thị", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}