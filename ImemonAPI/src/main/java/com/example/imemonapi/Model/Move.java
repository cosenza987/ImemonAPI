package com.example.imemonapi.Model;

public class Move {
    private int id;
    private String name;
    private String effect;


    // All-argument constructor
    public Move(int id, String name, String effect) {
        this.id = id;
        this.name = name;
        this.effect = effect;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for effect
    public String getEffect() {
        return effect;
    }

    // Setter for effect
    public void setEffect(String effect) {
        this.effect = effect;
    }
}