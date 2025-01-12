package hcmuaf.nlu.edu.vn.quanlyxemphim.filter;

import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "user-filter", urlPatterns = {"/reservation", "/create-rating", "/submitReservation", "/payVNPAY","/addToFavorites"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false); // Không tạo session mới nếu chưa đăng nhập
        Users user = (session != null) ? (Users) session.getAttribute("user") : null;

        if (user == null || !"user".equalsIgnoreCase(user.getRole())) {
            // Lưu URL hiện tại
            String currentUrl = req.getRequestURI();
            String queryString = req.getQueryString();
            if (queryString != null) {
                currentUrl += "?" + queryString; // Gắn query string nếu có
            }
            req.getSession(true).setAttribute("redirectUrl", currentUrl);

            req.getRequestDispatcher ("/users/page/login-signup.jsp").forward(req,res); // Redirect đến trang login nếu không phải user
        } else {
            chain.doFilter(request, response); // Nếu là user, tiếp tục xử lý
        }
    }

    @Override
    public void destroy() {
    }
}
