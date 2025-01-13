package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.reservation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/reservation")
public class ReservationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ URL
        String roomId = request.getParameter("roomId");
        String timeSlotId = request.getParameter("timeSlotId");
        String movieId = request.getParameter("movieId");
        String startTime = request.getParameter("startTime");
        HttpSession session = request.getSession();
        session.setAttribute("movieId", movieId);

        // Lưu thông tin để truyền sang trang JSP
        request.setAttribute("startTime", startTime);
        request.setAttribute("roomId", roomId);
        request.setAttribute("timeSlotId", timeSlotId);

        // Chuyển hướng đến trang đặt chỗ
        request.getRequestDispatcher("users/page/reservation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
