package com.example.imemonapi.Responses;

import com.example.imemonapi.Model.Pokedex;
import com.example.imemonapi.Model.PokedexEntryClass;

import java.util.ArrayList;

public class PokedexResponse {
    private int status;
    private String message;
    private ArrayList<PokedexEntryClass> pokedex;

    public PokedexResponse(int status, String message, ArrayList<PokedexEntryClass> pokedex) {
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

    public ArrayList<PokedexEntryClass> getPokedex() {
        return pokedex;
    }

    public void setPokedex(ArrayList<PokedexEntryClass> pokedex) {
        this.pokedex = pokedex;
    }
}
