package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.Users;


import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SignUpDao {
    private final DBConnect dbConnect;

    public SignUpDao() {
        this.dbConnect = new DBConnect();
    }
    // Kiểm tra email và tên tài khoản đã tồn tại
    public boolean checkExistence(String email, String username) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE username = ? OR email = ? LIMIT 1";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        }

    }
    // Thêm người dùng vào bảng users khi chưa xác thực email
    public boolean addUser(Users user) throws SQLException {
        String sql = "INSERT INTO users (email, username, password, isEmailVerified,role, createDate, updateDate) VALUES (?, ?, ?, 0,?, NOW(), NOW())";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            stmt.setString(4,"user");
            return stmt.executeUpdate() > 0;
        }
    }

    // Thêm thông tin vào bảng xác thực email
    public boolean addEmailVerification(String email, String code, int userId) throws SQLException {
        String sql = "INSERT INTO verifyemail (email, code, codeExpiry, userId) VALUES (?, ?, DATE_ADD(NOW(), INTERVAL 3 MINUTE), ?)";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, email);  // Thêm email vào câu lệnh
            stmt.setString(2, code);   // Thêm mã xác thực vào câu lệnh
            stmt.setInt(3, userId);    // Thêm userId vào câu lệnh

            // Thực thi câu lệnh và trả về kết quả (true nếu thành công, false nếu không)
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Xử lý lỗi SQL và in ra chi tiết lỗi
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }finally {
            dbConnect.closeConnection(); // Đảm bảo đóng kết nối sau khi sử dụng
        }
    }

    // Cập nhật trạng thái xác thực email
    public boolean verifyEmail(String email) throws SQLException {
        String sql = "UPDATE users SET isEmailVerified = 1 WHERE email = ?";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;
        }finally {
            dbConnect.closeConnection(); // Đảm bảo đóng kết nối sau khi sử dụng
        }
    }
    //Lấy userId
    public int getUserIdByEmail(String email) throws SQLException {
        String sql = "SELECT id FROM users WHERE email = ?";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1; // Trả về -1 nếu không tìm thấy
            }
        }finally {
            dbConnect.closeConnection(); // Đảm bảo đóng kết nối sau khi sử dụng
        }
    }
}
