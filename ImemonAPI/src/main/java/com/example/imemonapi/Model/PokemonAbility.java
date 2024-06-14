package com.example.imemonapi.Model;

public class PokemonAbility {
    private int id;
    private int pokemonId;
    private int abilityId;


    public PokemonAbility(int id, int pokemonId, int abilityId) {
        this.id = id;
        this.pokemonId = pokemonId;
        this.abilityId = abilityId;
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

    public int getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(int abilityId) {
        this.abilityId = abilityId;
    }
}