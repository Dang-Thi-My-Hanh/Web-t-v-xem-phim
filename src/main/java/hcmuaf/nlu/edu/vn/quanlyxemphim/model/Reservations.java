package hcmuaf.nlu.edu.vn.quanlyxemphim.model;

import java.sql.Timestamp;

public class Reservations {
    private int id;
    private int movieId;
    private int roomId;
    private Timestamp timeSlotId;
    private String customerName;
    private String  customerPhone;
    private  int seats;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Timestamp getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Timestamp timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
