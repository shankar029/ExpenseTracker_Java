package com.expensetracker.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    private String token;
    
    @JsonProperty("user_info")
    private UserResponse userInfo;

    // Constructors
    public LoginResponse() {}

    public LoginResponse(String token, UserResponse userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponse getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserResponse userInfo) {
        this.userInfo = userInfo;
    }
}