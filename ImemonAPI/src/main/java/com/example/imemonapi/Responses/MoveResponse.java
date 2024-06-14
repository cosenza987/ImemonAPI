package com.example.imemonapi.Responses;

import com.example.imemonapi.Model.Move;

import java.util.ArrayList;

public class MoveResponse {
    private int status;
    private String message;
    private ArrayList<Move> moves;

    public MoveResponse(int status, String message, ArrayList<Move> moves) {
        this.status = status;
        this.message = message;
        this.moves = moves;
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

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }
}
