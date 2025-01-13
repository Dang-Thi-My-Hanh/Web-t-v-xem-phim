package hcmuaf.nlu.edu.vn.quanlyxemphim.service;

import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.reservation.ReservationDAO;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Reservations;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Room;

import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService() {
        reservationDAO = new ReservationDAO();

    }

    public boolean createReservation(int userId, String roomId, String timeSlotId, int movieId, String customerName, String customerPhone, int seatNumber) {
        return reservationDAO.createReservation(userId, roomId, timeSlotId, movieId, customerName, customerPhone, seatNumber);
    }

    public Room getRoomById(String roomId) {
        return reservationDAO.getRoomById(roomId);
    }

    public void updateReservationsStatus(int userId) {
        reservationDAO.updateReservationsStatus(userId);
    }

    //     lấy ra đơn hàng
    public List<Reservations> getAllReservation() throws SQLException {
        return reservationDAO.getAllReservation();
    }

    // xóa đơn hàng
    public boolean deleteReservations(String id) {
        return reservationDAO.deleteReservations(id);
    }

    // Cập nhật lại doanh thu khi thanh toán
    public boolean updateRevenue(String movieId, double ticketPrice) {
        return reservationDAO.updateRevenue(movieId, ticketPrice);
    }

}
