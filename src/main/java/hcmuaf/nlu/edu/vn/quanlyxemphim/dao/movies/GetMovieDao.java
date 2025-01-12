package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.movies;



import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetMovieDao {
    private final DBConnect dbConnect;

    public GetMovieDao() {
        dbConnect = new DBConnect();
    }

    public Movie getMovie(int id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        Movie movie = null; // Khởi tạo giá trị ban đầu là null để kiểm tra sau
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setInt(1, id); // Gán tham số id vào câu lệnh SQL
            try (ResultSet rs = ptm.executeQuery()) {
                if (rs.next()) {
                    // Nếu tìm thấy phim, tạo đối tượng Movie và thiết lập các giá trị
                    movie = new Movie();
                    movie.setId(rs.getInt("id"));
                    movie.setTitle(rs.getString("title"));
                    movie.setDescription(rs.getString("description"));
                    movie.setTicketPrice(rs.getDouble("ticketPrice"));
                    movie.setRevenue(rs.getDouble("revenue"));
                    movie.setGenre(rs.getString("genre"));
                    movie.setReleaseDate(rs.getTimestamp("releaseDate"));
                    movie.setPosterUrl(rs.getString("posterUrl"));
                    movie.setDuration(rs.getInt("duration"));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching movie with ID: " + id, e);
        }
        return movie; // Trả về null nếu không tìm thấy phim
    }

}
