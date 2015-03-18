package com.cmbb.smartkids.account;

/**
 * Created by N.Sun
 */
public class User {

    protected String username;
    protected String phone;
    protected String token;
    protected String avatarUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
