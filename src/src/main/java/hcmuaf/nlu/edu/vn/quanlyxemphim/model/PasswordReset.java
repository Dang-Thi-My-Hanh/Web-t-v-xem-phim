package hcmuaf.nlu.edu.vn.quanlyxemphim.model;

import java.sql.Timestamp;

public class PasswordReset {
    private int id;
    private int userId;
    private String resetToken;
    private Timestamp tokenExpire;
    public PasswordReset() {}
    public PasswordReset(int id, int userId, String resetToken, Timestamp tokenExpire) {
        this.id = id;
        this.userId = userId;
        this.resetToken = resetToken;
        this.tokenExpire = tokenExpire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Timestamp getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Timestamp tokenExpire) {
        this.tokenExpire = tokenExpire;
    }
}
