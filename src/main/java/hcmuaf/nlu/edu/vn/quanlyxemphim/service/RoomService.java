package hcmuaf.nlu.edu.vn.quanlyxemphim.service;

import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.room.RoomDao;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Room;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.TimeSlot;

import java.util.List;

public class RoomService {

    private RoomDao roomDao;
    public RoomService() {
        roomDao = new RoomDao();
    }

      public List<Room> getRooms() {
        return roomDao.getRooms();
      }

    public List<TimeSlot> getTimeSlots() {
        return roomDao.getTimeSlots();
    }
}
