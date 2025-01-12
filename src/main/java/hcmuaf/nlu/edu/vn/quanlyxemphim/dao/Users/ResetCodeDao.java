package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.Users;



import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetCodeDao {
    private final DBConnect dbConnect;

    public ResetCodeDao() {
         this.dbConnect = new DBConnect();
    }

    // Cập nhật lại mã code xác thực
    public void updateVerificationCode(String email, String code) throws SQLException {
        String sql = "UPDATE verifyemail SET code = ?, codeExpiry = DATE_ADD(NOW(), INTERVAL 3 MINUTE) WHERE email = ?";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, code);
            stmt.setString(2, email);
            stmt.executeUpdate();
        }finally {
            dbConnect.closeConnection(); // Đảm bảo đóng kết nối sau khi sử dụng
        }
    }
}
