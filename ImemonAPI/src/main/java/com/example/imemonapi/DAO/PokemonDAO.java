package com.example.imemonapi.DAO;

import com.example.imemonapi.ConnectionFactory;
import com.example.imemonapi.Model.Pokemon;
import com.example.imemonapi.Model.SimplePokedexEntryClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonDAO {
    Connection db;
    public PokemonDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
        String creation = "CREATE TABLE IF NOT EXISTS pokemon (id int, attack int, defense int, flavor varchar(255), hp int, image varchar(255), name varchar(255), sp_att int, sp_def int, speed int, total int, PRIMARY KEY (id));";
        PreparedStatement ps = db.prepareStatement(creation);
        ps.executeUpdate();
    }
    public Pokemon findById(int id) throws SQLException {
        String sql = "SELECT * FROM pokemon WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractPokemon(resultSet);
                }
            }
        }
        return null;
    }

    // Method to fetch all Pokemon from the database
    public ArrayList<Pokemon> findAll() throws SQLException {
        ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
        String sql = "SELECT * FROM pokemon";
        try (PreparedStatement statement = db.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Pokemon pokemon = extractPokemon(resultSet);
                pokemonArrayList.add(pokemon);
            }
        }
        return pokemonArrayList;
    }

    // Method to retrieve simplified Pokédex entries (SimplePokedexEntryClass)
    public ArrayList<SimplePokedexEntryClass> findSimpleAll() throws SQLException {
        ArrayList<SimplePokedexEntryClass> simplePokedex = new ArrayList<>();
        String sql = "SELECT id, name, image FROM pokemon";
        try (PreparedStatement statement = db.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                SimplePokedexEntryClass simpleEntry = new SimplePokedexEntryClass(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image")
                );
                simplePokedex.add(simpleEntry);
            }
        }
        return simplePokedex;
    }

    // Helper method to extract Pokemon object from ResultSet
    private Pokemon extractPokemon(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String image = resultSet.getString("image");
        int total = resultSet.getInt("total");
        int hp = resultSet.getInt("hp");
        int attack = resultSet.getInt("attack");
        int defense = resultSet.getInt("defense");
        int spAtt = resultSet.getInt("sp_att");
        int spDef = resultSet.getInt("sp_def");
        int speed = resultSet.getInt("speed");
        String flavor = resultSet.getString("flavor");

        return new Pokemon(id, name, image, total, hp, attack, defense, spAtt, spDef, speed, flavor);
    }
}
