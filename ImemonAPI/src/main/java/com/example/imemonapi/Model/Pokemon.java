package com.example.imemonapi.Model;

public class Pokemon {
    private int id, total, hp, attack, defense, spAtt, spDef, speed;
    private String name, image, flavor;
    public Pokemon(int id, String name, String image, int total, int hp, int attack, int defense, int spAtt, int spDef, int speed, String flavor) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAtt = spAtt;
        this.spDef = spDef;
        this.speed = speed;
        this.flavor = flavor;
    }
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
    public String toString() {
        return "{"
                + "\"id\":" + id + ","
                + "\"total\":" + total + ","
                + "\"hp\":" + hp + ","
                + "\"attack\":" + attack + ","
                + "\"defense\":" + defense + ","
                + "\"spAtt\":" + spAtt + ","
                + "\"spDef\":" + spDef + ","
                + "\"speed\":" + speed + ","
                + "\"name\":\"" + name + "\","
                + "\"image\":\"" + image + "\","
                + "\"flavor\":\"" + flavor + "\""
                + "}";
    }
}
