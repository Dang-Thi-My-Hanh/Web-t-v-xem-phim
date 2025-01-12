package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.Users;



import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class VerifyEmailDao {
    private final DBConnect dbConnect;

    public VerifyEmailDao() {
        this.dbConnect = new DBConnect();
    }
    //Xác thực email
    public boolean isCode(String email, String code) throws SQLException {
        String sql = "SELECT code, codeExpiry  FROM verifyemail  WHERE email = ?";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedCode = rs.getString("code");
                Timestamp codeExpiry = rs.getTimestamp("codeExpiry");
                // Kiểm tra mã xác thực có trùng và chưa hết hạn
                return storedCode.equals(code) && codeExpiry != null && codeExpiry.after(new Timestamp(System.currentTimeMillis())); // Mã hợp lệ và chưa hết hạn
            }
            return false; // Mã không tồn tại trong hệ thống
        }finally {
            dbConnect.closeConnection(); // Đảm bảo đóng kết nối sau khi sử dụng
        }
    }

    // Xóa thông tin xác thực
    public void deleteVerificationRecord(String email) throws SQLException {
        String sql = "DELETE FROM verifyemail WHERE email = ?";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        }finally {
            dbConnect.closeConnection(); // Đảm bảo đóng kết nối sau khi sử dụng
        }
    }
}
