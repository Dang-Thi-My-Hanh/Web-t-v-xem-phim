package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.reservation;

import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Reservations;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReservationDAO {
    private final DBConnect dbConnect;

    public ReservationDAO() {
        dbConnect = new DBConnect();
    }

    public boolean createReservation(int userId, String roomId, String timeSlotId, int movieId, String customerName, String customerPhone, int seatNumber) {
        // Kết nối cơ sở dữ liệu
        String query = "INSERT INTO reservations (userId,roomId, timeSlotId, movieId, customerName, customerPhone, seats, status) " +
                "VALUES (?, ?, ?, ?, ?,?,?, ?)";

        try (PreparedStatement preparedStatement = dbConnect.preparedStatement(query)) {

            // Thiết lập các tham số cho truy vấn
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, roomId);
            preparedStatement.setString(3, timeSlotId);
            preparedStatement.setInt(4, movieId);
            preparedStatement.setString(5, customerName);
            preparedStatement.setString(6, customerPhone);
            preparedStatement.setInt(7, seatNumber); // Số ghế đặt
            preparedStatement.setString(8, "chưa thanh toán");

            // Thực thi truy vấn
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Lấy phòng theo id
    public Room getRoomById(String roomId) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        Room room = null;
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, roomId);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setCapacity(rs.getInt("capacity"));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;
    }

    //Cập nhật trang thái đặt chỗ khi thanh toán thành công
    public void updateReservationsStatus(int userId) {
        // Cập nhật trạng thái đặt chỗ trong cơ sở dữ liệu
        String updateQuery = "UPDATE reservations SET status = ? WHERE userId = ?";

        try (PreparedStatement stmt = dbConnect.preparedStatement(updateQuery)) {
            stmt.setString(1, "Đã thanh toán");  // "PAID" là trạng thái thành công
            stmt.setInt(2, userId);
            stmt.executeUpdate();  // Thực hiện cập nhật

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //     lấy ra đơn hàng
    public List<Reservations> getAllReservation() throws SQLException {
        String sql = "SELECT * FROM reservations";
        List<Reservations> reservations = new ArrayList<>();
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Reservations reservation = new Reservations();
                reservation.setId(rs.getInt("id"));  // Lấy id vé
                reservation.setCustomerName(rs.getString("customerName"));  // Lấy customerName
                reservation.setCustomerPhone(rs.getString("customerPhone"));  // Lấy customerPhone
                reservation.setStatus(rs.getString("status"));  // Lấy status vé
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    // Xóa phim theo ID
    public boolean deleteReservations(String id) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, id); // Gán ID của phim cần xóa

            int rowsDeleted = stmt.executeUpdate(); // Thực hiện câu lệnh SQL
            return rowsDeleted > 0; // Trả về true nếu có ít nhất 1 dòng được xóa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi xảy ra hoặc không có dòng nào bị xóa
    }

    // Cập nhật lại doanh thu khi thanh toán
    public boolean updateRevenue(String movieId, double ticketPrice) {
        String sql = "UPDATE movies SET revenue = revenue + ? WHERE id = ?";
        try (PreparedStatement pstmt = dbConnect.preparedStatement(sql)) {
            pstmt.setDouble(1, ticketPrice); // Giá vé để cộng thêm
            pstmt.setString(2, movieId);       // ID phim cần cập nhật
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

