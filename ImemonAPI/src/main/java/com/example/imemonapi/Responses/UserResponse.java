package com.example.imemonapi.Responses;

public class UserResponse {
    private int status;
    private String message;
    private String username;
    private String session;

    public UserResponse(int status, String message, String username, String session) {
        this.status = status;
        this.message = message;
        this.username = username;
        this.session = session;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}