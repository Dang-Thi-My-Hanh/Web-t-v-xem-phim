package hcmuaf.nlu.edu.vn.quanlyxemphim.service;



import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.rating.RatingDao;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Rating;

import java.util.List;

public class RatingService {
    private final RatingDao ratingDao;
    public RatingService() {
        ratingDao = new RatingDao();
    }

    // Lấy danh sách đánh giá
    public List<Rating> getListRating() {
        return ratingDao.getListRating();
    }

    //Xóa đánh giá
    public boolean deleteRating(int id) {
        return ratingDao.deleteRating(id);
    }

    // Taạo đánh giá
    public boolean addRating(Rating rating) {
        return ratingDao.addRating(rating);
    }


    //Tìm kiếm theo tid sản phẩm
    public List<Rating> getListRatingByProductId(String productId) {
        return ratingDao.getListRattingByMovies(productId);
    }
}
