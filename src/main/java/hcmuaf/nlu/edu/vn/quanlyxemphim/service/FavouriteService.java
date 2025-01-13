package hcmuaf.nlu.edu.vn.quanlyxemphim.service;

import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.favourite.FavouriteDao;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Movie;

import java.util.List;

public class FavouriteService {
    private final FavouriteDao favouriteDao;
    public FavouriteService() {
        favouriteDao = new FavouriteDao();
    }


    // Thêm phim vào yêu thích
    public void addToFavorites(long movieId, long userId) {
        favouriteDao.addToFavorites(movieId, userId);
    }

    // Lấy danh sách phim yêu thích
    public List<Movie> getFavoritesByUserId(long userId) {
        return favouriteDao.getFavoritesByUserId(userId);
    }
    // Xóa phim ra mục yêu thích
    public boolean removeFavorite(int userId, String movieId) {
        return favouriteDao.removeFavorite(userId, movieId);
    }

    public int getFavoriteCount(int userId) {
        return favouriteDao.getFavoriteCount(userId);
    }
}
