package hcmuaf.nlu.edu.vn.quanlyxemphim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
    private static Connection conn;
    private static final String URL = "jdbc:mysql://" + DBProperties.host() + ":" + DBProperties.port() + "/" +
            DBProperties.name() + "?" + DBProperties.option();

    // Phương thức lấy PreparedStatement
    public PreparedStatement preparedStatement(String sql) {
        try {
            // Kiểm tra kết nối
            if (conn == null || conn.isClosed()) {
                // Load driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Tạo kết nối
                conn = DriverManager.getConnection(URL, DBProperties.username(), DBProperties.password());
            }

            // Trả về PreparedStatement
            return conn.prepareStatement(sql);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null; // Nếu có lỗi, trả về null
        }
    }

    // Phương thức đóng kết nối
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

