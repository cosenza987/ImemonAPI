package com.example.imemonapi.Model;

import java.util.ArrayList;

public class PokedexEntryClass {
    private int id;
    private String name;
    private String image;
    private int total;
    private int hp;
    private int attack;
    private int defense;
    private int spAtt;
    private int spDef;
    private int speed;
    private ArrayList<String> types;

    public PokedexEntryClass(Pokemon pokemon, ArrayList<String> types) {
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.image = pokemon.getImage();
        this.total = pokemon.getTotal();
        this.hp = pokemon.getHp();
        this.attack = pokemon.getAttack();
        this.defense = pokemon.getDefense();
        this.spAtt = pokemon.getSpAtt();
        this.spDef = pokemon.getSpDef();
        this.speed = pokemon.getSpeed();
        this.types = types;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpAtt() {
        return spAtt;
    }

    public void setSpAtt(int spAtt) {
        this.spAtt = spAtt;
    }

    public int getSpDef() {
        return spDef;
    }

    public void setSpDef(int spDef) {
        this.spDef = spDef;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }
}
