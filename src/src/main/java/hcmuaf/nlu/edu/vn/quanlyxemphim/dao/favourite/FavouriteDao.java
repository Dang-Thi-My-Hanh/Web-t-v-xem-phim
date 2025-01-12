package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.favourite;

import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavouriteDao {
    private final DBConnect dbConnect;
    public FavouriteDao() {
        dbConnect = new DBConnect();
    }


    //Thêm phim yêu thích
    public void addToFavorites(long movieId, long userId) {
        // Kiểm tra xem phim đã tồn tại trong danh sách yêu thích chưa
        String checkQuery = "SELECT COUNT(*) FROM Favorites WHERE userId = ? AND movieId = ?";

        try (PreparedStatement checkStmt = dbConnect.preparedStatement(checkQuery)) {
            checkStmt.setLong(1, userId);
            checkStmt.setLong(2, movieId);

            // Thực thi truy vấn kiểm tra
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    // Nếu phim đã tồn tại, thay thế bằng phim mới (ví dụ, update thông tin phim nếu cần thiết)
                    String updateQuery = "UPDATE Favorites SET movieId = ? WHERE userId = ?";

                    try (PreparedStatement updateStmt = dbConnect.preparedStatement(updateQuery)) {
                        updateStmt.setLong(1, movieId);
                        updateStmt.setLong(2, userId);
                        updateStmt.executeUpdate();
                    }
                    return; // Thoát khỏi phương thức vì đã thực hiện thay thế phim
                }
            }

            // Nếu phim chưa có, thêm phim vào danh sách yêu thích
            String insertQuery = "INSERT INTO Favorites (userId, movieId) VALUES (?, ?)";
            try (PreparedStatement insertStmt = dbConnect.preparedStatement(insertQuery)) {
                insertStmt.setLong(1, userId);
                insertStmt.setLong(2, movieId);
                insertStmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    // Lấy danh sách phim yêu thích của người dùng
    public List<Movie> getFavoritesByUserId(long userId) {
        List<Movie> favoriteMovies = new ArrayList<>();
        String query = "SELECT m.id, m.title, m.posterUrl, m.duration " +
                "FROM `Movies` m " +
                "JOIN `Favorites` f ON m.id = f.movieId " +
                "WHERE f.userId = ?";

        try (PreparedStatement stmt = dbConnect.preparedStatement(query)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setPosterUrl(rs.getString("posterUrl"));
                movie.setDuration(rs.getInt("duration"));
                favoriteMovies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Thông báo lỗi hoặc xử lý
        }

        return favoriteMovies;
    }

    // Xóa phim khỏi yêu thích
    public boolean removeFavorite(int userId, String movieId) {
        String query = "DELETE FROM Favorites WHERE userId = ? AND movieId = ?";

        try (PreparedStatement stmt = dbConnect.preparedStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, movieId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Trả về true nếu xóa thành công, false nếu không
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy số lượng phim trong mục yêu thích
    public int getFavoriteCount(int userId) {
        String query = "SELECT COUNT(*) AS total FROM Favorites WHERE userId = ?";

        try (PreparedStatement stmt = dbConnect.preparedStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total"); // Trả về tổng số lượng phim
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy dữ liệu
    }
}

