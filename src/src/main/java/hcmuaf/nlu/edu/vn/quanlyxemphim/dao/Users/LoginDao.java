package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.Users;



import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private final DBConnect dbConnect;

    public LoginDao() {
        this.dbConnect = new DBConnect();
    }



    // Xử lý đăng nhập
    public boolean checkLogin(String username, String password) throws SQLException {
        String sql = "SELECT password FROM users WHERE username=?";

        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, username);

            try (ResultSet rs = ptm.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");

                    // So sánh mật khẩu nhập vào với mật khẩu đã mã hóa
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        return true; // Đăng nhập thành công
                    }
                }
            }
        }finally {
            dbConnect.closeConnection(); // Đảm bảo đóng kết nối sau khi sử dụng
        }
        return false; // Tên đăng nhập hoặc mật khẩu không đúng
    }

    //Lấy user
    public Users getUser(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        try(PreparedStatement ptm = dbConnect.preparedStatement(sql)){
            ptm.setString(1, username);
            ResultSet rs = ptm.executeQuery();
            Users user = new Users();
            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setIsEmailVerified(rs.getInt("isEmailVerified"));
                user.setStatus(rs.getString("status"));
               return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbConnect.closeConnection(); //  đóng kết nối sau khi sử dụng
        }
    }
}

