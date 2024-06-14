package com.example.imemonapi.Model;

import java.util.ArrayList;

public class Pokedex {
    private ArrayList<PokedexEntryClass> pokedex;

    public Pokedex(ArrayList<PokedexEntryClass> pokedex) {
        this.pokedex = pokedex;
    }

    public ArrayList<PokedexEntryClass> getPokedex() {
        return pokedex;
    }

    public void setPokedex(ArrayList<PokedexEntryClass> pokedex) {
        this.pokedex = pokedex;
    }
}