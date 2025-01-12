package hcmuaf.nlu.edu.vn.quanlyxemphim.dao.movies;


import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.DBConnect;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    private DBConnect dbConnect;

    public MovieDao() {
        this.dbConnect = new DBConnect();
    }


    // Lấy ra danh sách hình ảnh poster của phim
    public List<String> getListPoster() {
        String sql = "SELECT posterUrl FROM movies";
        List<String> posterUrls = new ArrayList<>();
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql);
             ResultSet rs = ptm.executeQuery()) {
            while (rs.next()) {
                posterUrls.add(rs.getString("posterUrl"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posterUrls;
    }

    // Lấy ra danh sách tất cả phim
    public List<Movie> getAllMovies() throws SQLException {
        String sql = "SELECT * FROM movies";
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movies.add(mapResultSetToMovie(rs));
            }
        }
        return movies;
    }


    // Lấy ra sản phẩm của danh mục dựa vào thể loại
    public List<Movie> getAllMoviesByGenre(int categoryId) throws SQLException {
        String sql = "SELECT * FROM movies WHERE genre = ?";
        List<Movie> movies = new ArrayList<>();

        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setInt(1, categoryId); // set categoryId =1
            if (stmt == null) {
                throw new SQLException("Failed to create PreparedStatement");
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    movies.add(mapResultSetToMovie(rs));
                }
            }
        }
        return movies;
    }

    // Hàm thêm phim
    public boolean addMovie(Movie movie) {
        String sql = "INSERT INTO movies (title, description, genre, releaseDate, posterUrl,duration,createdAt,ticketPrice) " +
                "VALUES (?, ?, ?, NOW(),?, ?,NOW(),?)";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            // Gán giá trị cho các tham số trong câu lệnh SQL
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getGenre());
            stmt.setString(4, movie.getPosterUrl());
            stmt.setInt(5, movie.getDuration());
            stmt.setDouble(6,movie.getTicketPrice());

            // Thực hiện thêm dữ liệu vào bảng
            int rowsAdded = stmt.executeUpdate();
            return rowsAdded > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu thêm thất bại
        }
    }


    // Phương thức cập nhật sản phẩm trong cơ sở dữ liệu
// Cập nhật thông tin phim
    public boolean updateMovie(int id, Movie movie) {
        String sql = "UPDATE movies SET title = ?, description = ?, genre = ?, posterUrl = ?, duration = ?, ticketPrice = ?, updatedAt = NOW() WHERE id = ?";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            // Gán giá trị cho các tham số trong câu lệnh SQL
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getGenre());
            stmt.setString(4, movie.getPosterUrl());
            stmt.setInt(5, movie.getDuration());
            stmt.setDouble(6, movie.getTicketPrice());
            stmt.setInt(7, id);

            int rowsUpdated = stmt.executeUpdate(); // Thực hiện câu lệnh SQL
            return rowsUpdated > 0; // Trả về true nếu có ít nhất 1 dòng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu xảy ra lỗi hoặc không có dòng nào được cập nhật
    }



    // Xóa phim theo ID
    public boolean deleteMovie(String id) {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql)) {
            stmt.setString(1, id); // Gán ID của phim cần xóa

            int rowsDeleted = stmt.executeUpdate(); // Thực hiện câu lệnh SQL
            return rowsDeleted > 0; // Trả về true nếu có ít nhất 1 dòng được xóa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi xảy ra hoặc không có dòng nào bị xóa
    }

    // Phương thức helper để map dữ liệu từ ResultSet vào đối tượng Movie
    private Movie mapResultSetToMovie(ResultSet rs) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setTitle(rs.getString("title"));
        movie.setDescription(rs.getString("description"));
        movie.setGenre(rs.getString("genre"));
        movie.setReleaseDate(rs.getTimestamp("releaseDate"));
        movie.setPosterUrl(rs.getString("posterUrl"));
        movie.setDuration(rs.getInt("duration"));
        movie.setRevenue(rs.getDouble("revenue"));
        movie.setTicketPrice(rs.getDouble("ticketPrice"));
        movie.setCreatedAt(Timestamp.valueOf(rs.getString("createdAt")));
        movie.setUpdatedAt(Timestamp.valueOf(rs.getString("updatedAt")));
        return movie;
    }

    // LẤY RA phim theo tên
    public List<Movie> getListMoviesByName(String title) throws SQLException {
        String sql = "SELECT * FROM movies WHERE title LIKE ? COLLATE utf8mb4_unicode_ci";
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, "%" + title + "%");
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                movies.add(mapResultSetToMovie(rs));
            }
        }
        return movies;
    }

    public List<Movie> getNewestMovies() {
        List<Movie> movies = new ArrayList<>();
        // Lấy tất cả các bộ phim, sắp xếp theo ngày tạo giảm dần (mới nhất lên đầu)
        String sql = "SELECT * FROM movies ORDER BY createdAt DESC";
        try (PreparedStatement stmt = dbConnect.preparedStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setGenre(rs.getString("genre"));
                movie.setReleaseDate(Timestamp.valueOf(rs.getString("releaseDate")));
                movie.setPosterUrl(rs.getString("posterUrl"));
                movie.setDuration(rs.getInt("duration"));
                movie.setRevenue(rs.getDouble("revenue"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // lấy ra top 10 bộ phim có doanh thu cao nhất.
    public List<Movie> getTop10HighestRevenueMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies ORDER BY revenue DESC LIMIT 10";

        try (PreparedStatement stmt = dbConnect.preparedStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setGenre(rs.getString("genre"));
                movie.setReleaseDate(Timestamp.valueOf(rs.getString("releaseDate")));
                movie.setPosterUrl(rs.getString("posterUrl"));
                movie.setDuration(rs.getInt("duration"));
                movie.setRevenue(rs.getDouble("revenue"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // Lấy ra danh sách các phim thể loại là phim Hành động
    public List<Movie> getActionMovies() throws SQLException {
        List<Movie> actionMovies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE genre = 'Hành động'"; // Câu lệnh SQL
        // Tạo statement và thực thi truy vấn
        try (Statement stmt = dbConnect.preparedStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Lấy thông tin từ kết quả truy vấn
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String genre = rs.getString("genre");
                Timestamp releaseDate = rs.getTimestamp("releaseDate");
                String posterUrl = rs.getString("posterUrl");
                int duration = rs.getInt("duration");
                double revenue = rs.getDouble("revenue");
                double ticketPrice = rs.getDouble("ticketPrice");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");
                // Tạo đối tượng Movie và thêm vào danh sách
                Movie movie = new Movie(id, title, description, genre, releaseDate, posterUrl, ticketPrice, duration, revenue, createdAt,updatedAt);
                actionMovies.add(movie);
            }
        }

        return actionMovies;
    }

    // Lấy ra danh sách các phim thể loại là phim Hài
    public List<Movie> getComedyMovies() throws SQLException {
        List<Movie> comedyMovies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE genre = 'Hài'"; // Câu lệnh SQL
        // Tạo statement và thực thi truy vấn
        try (Statement stmt = dbConnect.preparedStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Lấy thông tin từ kết quả truy vấn
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String genre = rs.getString("genre");
                Timestamp releaseDate = rs.getTimestamp("releaseDate");
                String posterUrl = rs.getString("posterUrl");
                int duration = rs.getInt("duration");
                double revenue = rs.getDouble("revenue");
                double ticketPrice = rs.getDouble("ticketPrice");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");
                // Tạo đối tượng Movie và thêm vào danh sách
                Movie movie = new Movie(id, title, description, genre, releaseDate, posterUrl, ticketPrice, duration, revenue, createdAt,updatedAt);
                comedyMovies.add(movie);
            }
        }
        return comedyMovies;
    }

    // Lấy ra danh sách các phim thể loại là phim Tình cảm
    public List<Movie> getRomanceMovie() throws SQLException {
        List<Movie> romanceMovies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE genre = 'Tình cảm'"; // Câu lệnh SQL
        // Tạo statement và thực thi truy vấn
        try (Statement stmt = dbConnect.preparedStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Lấy thông tin từ kết quả truy vấn
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String genre = rs.getString("genre");
                Timestamp releaseDate = rs.getTimestamp("releaseDate");
                String posterUrl = rs.getString("posterUrl");
                int duration = rs.getInt("duration");
                double revenue = rs.getDouble("revenue");
                double ticketPrice = rs.getDouble("ticketPrice");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");
                // Tạo đối tượng Movie và thêm vào danh sách
                Movie movie = new Movie(id, title, description, genre, releaseDate, posterUrl, ticketPrice, duration, revenue, createdAt,updatedAt);
                romanceMovies.add(movie);
            }
        }
        return romanceMovies;
    }
    public double getTicketPrice(String id) {
        String sql = "SELECT ticketPrice FROM movies WHERE id = ? ";
        double ticketPrice = 0;
        try (PreparedStatement ptm = dbConnect.preparedStatement(sql)) {
            ptm.setString(1, id);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                ticketPrice = rs.getDouble("ticketPrice");

            }
            return ticketPrice;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}



