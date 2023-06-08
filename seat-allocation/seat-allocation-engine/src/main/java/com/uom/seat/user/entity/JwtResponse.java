package com.uom.seat.user.entity;

import org.springframework.security.core.userdetails.User;

public class JwtResponse {

    private UserEntity user ;
    private String jwtToken;

    public JwtResponse(UserEntity user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
