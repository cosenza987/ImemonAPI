package com.example.imemonapi;

public class Pokemon {
    private int id, total, hp, attack, defense, spAtt, spDef, speed;
    private String name, image, flavor;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getFlavor() {
        return flavor;
    }
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
}
