package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.Users;


import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDeleteUpdateAccountInAdminDao {
    private final DBConnect dbConnect;
    public AddDeleteUpdateAccountInAdminDao() {
        dbConnect = new DBConnect();
    }

    // Thêm tài khoản
    public boolean addAccount (Users user) {
        String sql = "insert into users (username,password,email,role) values(?,?,?,?)";
        try(PreparedStatement ptm = dbConnect.preparedStatement(sql)){
            ptm.setString(1, user.getUsername());
            ptm.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            ptm.setString(3, user.getEmail());
            ptm.setString(4, user.getRole());

            int rowsInserted = ptm.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteAccount(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setInt(1, id);
            int rowsDeleted = ptm.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    }

