package com.example.imemonapi.Model;

public class PokemonType {
    private int id;
    private int pokemonId;
    private String type;
    

    public PokemonType(int id, int pokemonId, String typeId) {
        this.id = id;
        this.pokemonId = pokemonId;
        this.type = typeId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}