package hcmuaf.nlu.edu.vn.quanlyxemphim.model;

import java.util.Date;

public class Users {
    private int id;
    private String email;
    private String fullName;
    private String username;
    private String password;
    private String address;
    private String avatarPath;
    private String phoneNumber;
    private String role;
    private String status;
    private int isEmailVerified;
    private double totalPrice;
    private Date createDate;
    private Date updateDate;

    public Users() {
    }
    public Users(int id, String username, String email, String phoneNumber,String status,String role){
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.role = role;
    }
    public Users(String fullName, String phoneNumber, String address){
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public Users(String fullName,String email, String phoneNumber, String address,String avatarPath){
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatarPath = avatarPath;
    }

    public Users(int id, String email, String fullName,
                 String username, String password, String address,
                 String phoneNumber, String role, String status,
                 int isEmailVerified, Date createDate,
                 Date updateDate) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = status;
        this.isEmailVerified = isEmailVerified;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Users(int id, String fullName, double totalPrice) {
        this.id = id;
        this.fullName = fullName;
        this.totalPrice = totalPrice;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(int isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", isEmailVerified=" + isEmailVerified +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

}


