package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.Users;

import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoUserDao {

    private final DBConnect dbConnect;
    public InfoUserDao() {
        dbConnect = new DBConnect();
    }
    // Lấy thông tin user
    public Users getInfoUser (int id){
        String sql = "SELECT fullName, email, phoneNumber, address, avatarPath FROM users WHERE id = ?";
        try(PreparedStatement ptm = dbConnect.preparedStatement(sql)){
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            if(rs.next()){
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                String avatarPath = rs.getString("avatarPath");
                return new Users(fullName,email, phoneNumber, address,avatarPath);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    // Phương thức cập nhật avatar
    public boolean updateAvatar(int userId, String avatarPath) {
        String sql = "UPDATE users SET avatarPath = ? WHERE id = ?";
        try (PreparedStatement statement = dbConnect.preparedStatement(sql)) {
            statement.setString(1, avatarPath);
            statement.setInt(2, userId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
