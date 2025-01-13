package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.rating;



import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Rating;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDao {
    private final DBConnect dbConnect;

    public RatingDao() {
        dbConnect = new DBConnect();
    }

    // Lấy danh sách đánh giá
    public List<Rating> getListRating() {
        String sql = "select * from ratings";

        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ResultSet rs = ptm.executeQuery();
            List<Rating> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int productId = rs.getInt("movieId");
                int userId = rs.getInt("userId");
                String content = rs.getString("comment");
                Date createdAt = rs.getDate("createdAt");

                Rating rating = new Rating(id, productId, userId, content, createdAt);
                list.add(rating);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Xóa đánh giá
    public boolean deleteRating(int id) {
        String sql = "delete from ratings where id = ?";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setInt(1, id);
            int row = ptm.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Thêm đánh giá
    public boolean addRating(Rating rating) {
    String sql = "INSERT INTO ratings (movieId, userId, comment, createdAt) VALUES (?,?,?,NOW())";
    try(PreparedStatement ptm = dbConnect.preparedStatement(sql)){
        ptm.setInt(1, rating.getMovieId());
        ptm.setInt(2, rating.getUserId());
        ptm.setString(3, rating.getContent());

        int row = ptm.executeUpdate();
        if(row > 0){
            return true;
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return false;
    }


    //lấy đánh giá theo phim
    public List<Rating> getListRattingByMovies(String movieId) {
        String sql = "SELECT * FROM ratings WHERE movieId LIKE ?";
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, "%"+ movieId +"%");
            ResultSet rs = ptm.executeQuery();
            List<Rating> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int productID = rs.getInt("movieId");
                int userId = rs.getInt("userId");
                String content = rs.getString("comment");
                Date createdAt = rs.getDate("createdAt");

                Rating rating = new Rating(id, productID, userId, content, createdAt);
                list.add(rating);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
