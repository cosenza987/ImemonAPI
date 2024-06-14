package com.example.imemonapi.Model;

import java.util.ArrayList;

public class SimplePokedex {
    private ArrayList<SimplePokedexEntryClass> pokedex;


    public SimplePokedex(ArrayList<SimplePokedexEntryClass> pokedex) {
        this.pokedex = pokedex;
    }

    public ArrayList<SimplePokedexEntryClass> getPokedex() {
        return pokedex;
    }

    public void setPokedex(ArrayList<SimplePokedexEntryClass> pokedex) {
        this.pokedex = pokedex;
    }
}
