package com.example.imemonapi.Responses;

import com.example.imemonapi.Model.SimplePokedex;
import com.example.imemonapi.Model.SimplePokedexEntryClass;

import java.util.ArrayList;

public class SimplePokedexResponse {
    private int status;
    private String message;
    private ArrayList<SimplePokedexEntryClass> pokedex;


    public SimplePokedexResponse(int status, String message, ArrayList<SimplePokedexEntryClass> pokedex) {
        this.status = status;
        this.message = message;
        this.pokedex = pokedex;
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

    public ArrayList<SimplePokedexEntryClass> getPokedex() {
        return pokedex;
    }

    public void setPokedex(ArrayList<SimplePokedexEntryClass> pokedex) {
        this.pokedex = pokedex;
    }
}