package hcmuaf.nlu.edu.vn.quanlyxemphim.service;



import hcmuaf.nlu.edu.vn.quanlyxemphim.dao.Users.*;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.PasswordReset;
import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;


public class UserService {
    private final EmailUtilService emailUtil;
    private final SignUpDao signUpDao;
    private final LoginDao usersDao;
    private final ResetCodeDao resetCodeDao;
    private final VerifyEmailDao verifyEmailDao;
    private final LogoutDao logoutDao;
    private final ResetPasswordDao resetPasswordDao;
    private final GetListAccountDao getListAccountDao;
    private final UpdateInfoDao updateInfoDao;
    private final AddDeleteUpdateAccountInAdminDao addDeleteUpdateAccountInAdminDao;
    private  final InfoUserDao infoUserDao;


    public UserService() {
        this.emailUtil = new EmailUtilService();
        this.signUpDao = new SignUpDao();
        this.usersDao = new LoginDao();
        this.resetCodeDao = new ResetCodeDao();
        this.verifyEmailDao = new VerifyEmailDao();
        this.logoutDao = new LogoutDao();
        this.resetPasswordDao = new ResetPasswordDao();
        this.getListAccountDao = new GetListAccountDao();
        this.updateInfoDao = new UpdateInfoDao();
        this.addDeleteUpdateAccountInAdminDao = new AddDeleteUpdateAccountInAdminDao();
        this.infoUserDao = new InfoUserDao();

    }

    // Đăng ký người dùng mới
    public boolean registerUser(String email, String username, String password) throws SQLException {
        try {
            // Kiểm tra xem email và tên tài khoản đã tồn tại
            if (signUpDao.checkExistence(email, username)) {
                return false;
            }
            // Thêm người dùng vào cơ sở dữ liệu
            Users newUser = new Users();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPassword(password);  // Mã hóa mật khẩu
            newUser.setIsEmailVerified(0);  // Mặc định chưa xác minh email
            if (!signUpDao.addUser(newUser)) {
                return false;
            }
            // Lấy userId của người dùng vừa tạo
            int userId = signUpDao.getUserIdByEmail(email);
            // Tạo mã xác thực và gửi email
            String verificationCode = generateVerificationCode();
            emailUtil.sendEmailAsync(email, "Mã xác thực email" ,"Mã xác thực của bạn là: "+ verificationCode);

            // Lưu mã xác thực vào cơ sở dữ liệu
            return signUpDao.addEmailVerification(email, verificationCode, userId);
        } catch (SQLException e) {
            e.printStackTrace();  // Cần xem xét thêm cách xử lý lỗi
            return false;
        }
    }

    // Tạo mã xác thực ngẫu nhiên
    public String generateVerificationCode() {
        return String.format("%04d", (int) (Math.random() * 10000));
    }



    // kểm tra tính hợp lệ của mã code
    public boolean isCode(String email, String code) throws SQLException {
         return verifyEmailDao.isCode(email, code);

    }
    //Xóa thông tin xác thực
    public void deleteVerifyCode(String email) throws SQLException {
        verifyEmailDao.deleteVerificationRecord(email);
    }

    //Gửi lại mã xác thực
    public void sendCode(String email) throws SQLException {
        // Tạo mã xác thực và gửi email
        String verificationCode = generateVerificationCode();
        String Subject = "Mã  xác thực";
        emailUtil.sendEmailAsync(email, Subject , verificationCode);

        // Lưu mã xác thực vào cơ sở dữ liệu
        resetCodeDao.updateVerificationCode(email, verificationCode);
    }


    //kiểm tra Cập nhật trạng thái xác thực email
    public boolean verifyEmail(String email) throws SQLException {
        return signUpDao.verifyEmail(email);
    }

    //ĐĂNG  NHẬP
    //Đăng nhập
    public boolean login(String username, String password) throws SQLException {
        return usersDao.checkLogin(username, password);
    }

    //Lấy user
    public Users getUser(String username) {
        return usersDao.getUser(username);
    }

    // Cập nhật trạng thái user
    public boolean UpdateStatusUser (String status, int id) throws SQLException {
      return  logoutDao.UpdateStatusUser(status, id);
    }



    //LẤY LẠI MẬT  KHẨU
    // Lấy user theo email
    public Users findUserByEmail(String email) throws Exception {
        return resetPasswordDao.findUserByEmail(email);
    }

    // Tạo token
    public String generateResetToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    //Thêm token
    public void saveResetToken(int userId, String token) throws Exception {
        resetPasswordDao.savePasswordResetToken(userId, token);
    }

    // Tim token đã tồn tại trong database
    public PasswordReset findResetTokenByUserId(int userId) throws Exception {
        return resetPasswordDao.findPasswordResetByUserId(userId);
    }
    // Tim token đã tồn tại trong database
    public PasswordReset findResetTokenByToken(String token) throws Exception {
        return resetPasswordDao.findResetTokenByToken(token);
    }
    // Cập nhâ lại token
    public void updateResetTokenForEmail(String email, String newToken) throws Exception {
        resetPasswordDao.updateResetTokenAndExpiry(email, newToken);
    }

    //Cập nhật lại mật khẩu
    public void updatePassword(int id, String hashedPassword) throws Exception {
        resetPasswordDao.updatePassword(id, hashedPassword);
    }

    // Cập nhật lại resetPassword khi thành công
    public void invalidateToken(String token) throws Exception {
        resetPasswordDao.invalidateToken(token);
    }


    // Lấy ra danh sách người dùng
    public List<Users> getListUsers() throws Exception {
          return  getListAccountDao.getListAccount();
    }

    // cập nhật thông tin user
    public boolean setUpdateInfoUser(int id, Users user) throws Exception {
            return  updateInfoDao.updateInfo(id, user);
    }

    // ------ thêm sửa xóa tài khoản-----
    public boolean addAccount(String username, String password, String email, String role) throws SQLException {
        // Kiểm tra xem email và tên tài khoản đã tồn tại
        if (signUpDao.checkExistence(email, username)) {
            return false;
        }
        // Thêm người dùng vào cơ sở dữ liệu
        Users newUser = new Users();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(password);  // Mã hóa mật khẩu
        newUser.setRole(role);
        return  addDeleteUpdateAccountInAdminDao.addAccount(newUser);
    }

    //Xóa tài khoản
    public boolean deleteAccount(int id) {
        return addDeleteUpdateAccountInAdminDao.deleteAccount(id);
    }


    // Lấy danh sách user theo tên
    public List<Users> getListUsersByName(String name) {
        return getListAccountDao.getListUserByName(name);
    }
    public Users getInfoUser (int id){
        return infoUserDao.getInfoUser(id);
    }

    // cập nhật avartar
    public boolean updateAvatar(int userId, String avatarPath) {
        return infoUserDao.updateAvatar(userId, avatarPath);
    }
}


