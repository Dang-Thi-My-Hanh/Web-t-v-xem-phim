package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.admin.reservations;

import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Reservations;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.ReservationService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "reservationsLists", value = "/reservationsLists")
public class ReservationsListsController extends HttpServlet {

    private final ReservationService reservationService = new ReservationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String showAll = request.getParameter("showAll");
        String action = request.getParameter("action");

        // Kiểm tra có id và action remove hay không
        if (id != null && action != null && action.equals("remove")) {
            // Xóa đơn hàng
            reservationService.deleteReservations(id);
        }

        try {
            // Gọi phương thức để lấy tất cả đơn hàng từ service
            List<Reservations> reservationsList = null;
            try {
                reservationsList = reservationService.getAllReservation();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // Hiển thị tất cả dữ liệu theo yêu cầu
            if (showAll != null) {
                if (showAll.equals("reservations")) {
                    request.setAttribute("reservations", reservationsList);  // Hiển thị tất cả đơn hàng
                }
            } else {
                // Mặc định hiển thị tối đa 10 mục
                reservationsList = reservationsList.stream().limit(10).collect(Collectors.toList());
                request.setAttribute("reservations", reservationsList);
            }

            // Đặt danh sách đơn hàng vào request attribute để chuyển đến JSP
            request.setAttribute("reservationsList", reservationsList);
            // Forward dữ liệu đến trang JSP để hiển thị
            request.getRequestDispatcher("/admin/pages/reservations.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}