package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.room;

import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Room;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.TimeSlot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    private final DBConnect dbConnect;
    public RoomDao() {
        dbConnect = new DBConnect();
    }

    public List<Room> getRooms() {
        String sql = "SELECT * FROM rooms";
        List<Room> rooms = new ArrayList<>();

        try (PreparedStatement ptm = dbConnect.preparedStatement(sql);
             ResultSet rs = ptm.executeQuery()) {
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setCapacity(rs.getInt("capacity"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching rooms", e);
        }
            return rooms;
        }

    public List<TimeSlot> getTimeSlots() {
        String sql = "SELECT * FROM timeslots";
        List<TimeSlot> timeSlots = new ArrayList<>();

        try (PreparedStatement ptm = dbConnect.preparedStatement(sql);
             ResultSet rs = ptm.executeQuery()) {
            while (rs.next()) {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setId(rs.getInt("id"));
                timeSlot.setRoomId(rs.getInt("roomId"));
                timeSlot.setStartTime(rs.getTime("startTime").toLocalTime());
                timeSlot.setEndTime(rs.getTime("endTime").toLocalTime());
                timeSlots.add(timeSlot);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching time slots", e);
        }
        return timeSlots;
    }
}
