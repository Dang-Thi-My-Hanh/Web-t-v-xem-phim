package hcmuaf.nlu.edu.vn.quanlyxemphim.model;

import java.sql.Date;

public class Rating {
    private int id;
    private int movieId;
    private int userId;
    private String content;
    private Date createdAt;

    public Rating(int id, int movieId, int userId, String content, Date createdAt) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Rating(int movieId, int userId, String content) {
        this.movieId = movieId;
        this.userId = userId;
        this.content = content;


    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
