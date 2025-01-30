package com.redsocial.bohemia.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserS {

    @JsonIgnore
    private String user;

    @JsonIgnore
    private String pass;

    private String token;

    public UserS() {
    }

    public UserS(String user, String pass, String token) {
        this.user = user;
        this.pass = pass;
        this.token = token;
    }

    public String getUserS() {
        return user;
    }

    public void setUserS(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}