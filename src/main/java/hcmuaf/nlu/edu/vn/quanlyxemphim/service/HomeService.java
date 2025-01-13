package hcmuaf.nlu.edu.vn.quanlyxemphim.service;



import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.home.HomeDao;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;

import java.util.List;

public class HomeService {
    private final HomeDao homeDao;

    public HomeService() {
        this.homeDao = new HomeDao();
    }


    // Tổng số người dùng
    public int totalAccount() {
        return homeDao.totalAccount();
    }

    // Tổng số nhận xét
    public int totalRating() {
        return homeDao.totalRating();
    }

//    // Tổng doanh thu
    public double totalSale() {
        return homeDao.totalSale();
    }

}
