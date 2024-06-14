package com.example.imemonapi.Responses;

import com.example.imemonapi.Model.Team;

import java.util.ArrayList;

public class TeamResponse {
    private int status;
    private String message;
    private ArrayList<Team> teams;

    public TeamResponse(int status, String message, ArrayList<Team> teams) {
        this.status = status;
        this.message = message;
        this.teams = teams;
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

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
