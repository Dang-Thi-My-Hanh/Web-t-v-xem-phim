package hcmuaf.nlu.edu.vn.quanlyxemphim.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;

public class Movie {
    private int id;
    private String title;
    private String description;
    private String genre;
    private Timestamp releaseDate;
    private String posterUrl;
    private double revenue;
    private int duration;
    private double ticketPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public Movie() {
    }

    public Movie(int id, String title, String description, String genre, Timestamp releaseDate, String posterUrl, double revenue, int duration, double ticketPrice, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.revenue = revenue;
        this.duration = duration;
        this.ticketPrice = ticketPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Movie(String title, String description, String genre, String img, int durationInMinutes, double price) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.posterUrl = img;
        this.revenue = durationInMinutes;
        this.duration = durationInMinutes;
        this.ticketPrice = price;
    }

    public Movie(int movieId, String title, String description, String genre, String img, int durationInMinutes, double price) {
        this.id = movieId;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.posterUrl = img;
        this.revenue = durationInMinutes;
        this.duration = durationInMinutes;
        this.ticketPrice = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
