package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.Users;


import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.PasswordReset;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ResetPasswordDao {
    private final DBConnect dbConnect;

    public ResetPasswordDao() {
        dbConnect = new DBConnect();
    }

    // Tìm kiếm user với email yêu cầu quên mật khẩu
    public Users findUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, email);
            ResultSet rs = ptm.executeQuery();
            Users user = new Users();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                return user;
            }

        } finally {
            dbConnect.closeConnection();
        }
        return null;
    }

    //Lưu thông tin resetPassword
    public void savePasswordResetToken(int userId, String token) throws SQLException {
        String sql = "INSERT INTO passwordreset (userId, resetToken, tokenExpiry) VALUES (?, ?, DATE_ADD(NOW(), INTERVAL 24 HOUR))";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setInt(1, userId);
            ptm.setString(2, token);
            ptm.executeUpdate();
        } finally {
            dbConnect.closeConnection();
        }
    }

    // Lấy ra thông tin của resetToken theo userId
    public PasswordReset findPasswordResetByUserId(int userId ) throws SQLException {
        String sql = "SELECT * FROM passwordreset WHERE userId = ?";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setInt(1, userId);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                return new PasswordReset(rs.getInt("id"), rs.getInt("userId"), rs.getString("resetToken"), rs.getTimestamp("tokenExpiry"));
            }
        } finally {
            dbConnect.closeConnection();
        }
        return null;
    }
    // Lấy ra thông tin của resetToken theo token
    public PasswordReset findResetTokenByToken(String token ) throws SQLException {
        String sql = "SELECT * FROM passwordreset WHERE resetToken = ?";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, token);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                return new PasswordReset(rs.getInt("id"), rs.getInt("userId"), rs.getString("resetToken"), rs.getTimestamp("tokenExpiry"));
            }
        } finally {
            dbConnect.closeConnection();
        }
        return null;
    }

    // Cập nhật mật khẩu
    public void updatePassword(int id, String hashedPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, hashedPassword);
            ptm.setInt(2, id);
            ptm.executeUpdate();
        } finally {
            dbConnect.closeConnection();
        }
    }

    //Cập nhật thông tin resetToken khi người dùng gửi lại
    public void updateResetTokenAndExpiry(String email, String newToken) throws SQLException {
        String sql = "UPDATE passwordreset SET resetToken = ?, tokenExpiry = DATE_ADD(NOW(), INTERVAL 24 HOUR) " +
                "WHERE userId = (SELECT id FROM users WHERE email = ?)";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, newToken);
            ptm.setString(2, email);
            ptm.executeUpdate();
        } finally {
            dbConnect.closeConnection();
        }
    }

    // Cập   nhật  thông tin resetToken khi đặt lại pass thành  công
    public void invalidateToken(String token) throws SQLException {
        String sql = "UPDATE passwordreset SET resetToken = NULL, tokenExpiry = NULL WHERE resetToken = ?";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, token);
            ptm.executeUpdate();
        } finally {
            dbConnect.closeConnection();
        }
    }


}
