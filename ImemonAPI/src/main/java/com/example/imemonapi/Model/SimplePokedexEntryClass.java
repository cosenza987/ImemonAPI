package com.example.imemonapi.Model;

public class SimplePokedexEntryClass {
    private int id;
    private String name;
    private String image;

    public SimplePokedexEntryClass(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    public SimplePokedexEntryClass(Pokemon pokemon) {
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.image = pokemon.getImage();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
