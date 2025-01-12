package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.pay;




import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.util.VNPayUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(name = "VNPay", value = "/payVNPAY")
public class VNPayController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        double amount = Double.parseDouble(req.getParameter("actual-ticket-price"));
            // Tạo URL thanh toán VNPay
            String paymentUrl = VNPayUtil.createPaymentUrl(req, amount);
            System.out.println(paymentUrl);

            // Điều hướng người dùng tới URL thanh toán
            resp.sendRedirect(paymentUrl);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
