package com.example.imemonapi.Responses;

import com.example.imemonapi.Model.PokemonClass;

public class PokemonResponse {
    private int status;
    private String message;
    private PokemonClass pokemon;

    public PokemonResponse(int status, String message, PokemonClass pokemon) {
        this.status = status;
        this.message = message;
        this.pokemon = pokemon;
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

    public PokemonClass getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonClass pokemon) {
        this.pokemon = pokemon;
    }
}
