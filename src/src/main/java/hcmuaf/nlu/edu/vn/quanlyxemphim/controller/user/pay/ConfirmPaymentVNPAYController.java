package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.pay;


import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.EmailUtilService;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.ReservationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "ConfirmPayment", value = "/confirm-payment")
public class ConfirmPaymentVNPAYController extends HttpServlet {
    private final EmailUtilService emailUtilService = new EmailUtilService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy các tham số từ VNPay trả về
        String vnp_TxnRef = req.getParameter("vnp_TxnRef");
        String vnp_ResponseCode = req.getParameter("vnp_ResponseCode");
        String vnp_Amount = req.getParameter("vnp_Amount");
        // Kiểm tra trạng thái giao dịch
        String message;
        if ("00".equals(vnp_ResponseCode)) {
            message = "Thanh toán thành công! Mã giao dịch: " + vnp_TxnRef;
        } else {
            message = "Thanh toán thất bại! Vui lòng thử lại.";
        }
        // Chuyển các tham số tới JSP để hiển thị
        req.setAttribute("vnp_TxnRef", vnp_TxnRef);
        req.setAttribute("vnp_ResponseCode", vnp_ResponseCode);
        req.setAttribute("vnp_Amount", vnp_Amount);
        req.setAttribute("message", message);

        //lấy id hóa đơn từ session
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");

        ReservationService reservationService = new ReservationService();
        reservationService.updateReservationsStatus(user.getId());
        String movieId = (String) session.getAttribute("movieId");
        double ticketPrice = Double.parseDouble(vnp_Amount);
        reservationService.updateRevenue(movieId, ticketPrice/100);
        // Chuyển tiếp tới file JSP
        req.setAttribute("message", "Thanh toán thành công");
        String emailContent = "<html>"
                + "<body>"
                + "<h2>Cảm ơn bạn đã đặt vé tại hệ thống đặt vé của chúng tôi!</h2>"
                + "<p>Chúng tôi đã nhận được đơn đặt vé của bạn và thanh toán đã được xử lý thành công.</p>"
                + "<p>Thông tin đơn hàng của bạn:</p>"
                + "<ul>"
                + "</ul>"
                + "<p>Chúng tôi sẽ thông báo cho bạn khi vé được xác nhận và thông tin chi tiết được gửi đến email của bạn.</p>"
                + "<p>Chân thành cảm ơn sự tin tưởng của bạn!</p>"
                + "<footer>"
                + "<p>Trân trọng,<br> Hệ thống Đặt Vé Phim</p>"
                + "</footer>"
                + "</body>"
                + "</html>";

        // Gửi email xác nhận đến người dùng
        emailUtilService.sendEmailAsync(user.getEmail(), "Xác nhận thanh toán vé phim", emailContent);

        // Xóa giỏ hàng sau khi thanh toán thành công
        session.removeAttribute("movieId");
        req.getRequestDispatcher("users/page/check-pay.jsp").forward(req, resp);
    }
}
