package com.example.imemonapi.Model;

public class PokemonMove {
    private int id;
    private int pokemonId;
    private int moveId;

    public PokemonMove(int id, int pokemonId, int moveId) {
        this.id = id;
        this.pokemonId = pokemonId;
        this.moveId = moveId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }
}
