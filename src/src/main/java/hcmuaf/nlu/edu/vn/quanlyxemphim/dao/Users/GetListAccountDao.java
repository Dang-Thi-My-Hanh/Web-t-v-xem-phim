package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.Users;



import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetListAccountDao {
    private final DBConnect dbConnect;
    public GetListAccountDao() {
        dbConnect = new DBConnect();
    }

    //Lấy ra danh sách tài khoản
    public List<Users> getListAccount() {
        List<Users> listAccount = new ArrayList<>();
        String sql = "select * from users";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String status = rs.getString("status");
                String role = rs.getString("role");

                Users user = new Users(id, username, email, phoneNumber, status, role);
                listAccount.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listAccount;
    }

    public List<Users> getListUserByName(String name) {
        List<Users> listAccount = new ArrayList<>();
        String sql = "select * from users where username like ?";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, "%" + name + "%");
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String status = rs.getString("status");
                String role = rs.getString("role");

                Users user = new Users(id, username, email, phoneNumber, status, role);
                listAccount.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listAccount;

    }
}
