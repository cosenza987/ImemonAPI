package com.example.imemonapi.DAO;

import com.example.imemonapi.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonTypeDAO {
    Connection db;
    public PokemonTypeDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
        String creation = "CREATE TABLE IF NOT EXISTS pokemon_type (pokemon_id int, type_name varcHar(255));";
        PreparedStatement statement = db.prepareStatement(creation);
        statement.executeUpdate();
    }

    public ArrayList<String> findByPokemonId(long pokemonId) throws SQLException {
        ArrayList<String> pokemonTypes = new ArrayList<>();
        String sql = "SELECT * FROM pokemon_type WHERE pokemon_id = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setLong(1, pokemonId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pokemonTypes.add(extractPokemonType(resultSet));
                }
            }
        }
        return pokemonTypes;
    }

    private String extractPokemonType(ResultSet resultSet) throws SQLException {
        return resultSet.getString("type_name");
    }
}
