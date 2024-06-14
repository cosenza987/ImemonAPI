package com.example.imemonapi.Requests;

public class AuthRequest {
    private String session;
    private String username;

    public AuthRequest() {
    }

    public AuthRequest(String session, String username) {
        this.session = session;
        this.username = username;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
