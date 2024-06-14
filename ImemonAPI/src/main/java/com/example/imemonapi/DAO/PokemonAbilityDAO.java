package com.example.imemonapi.DAO;

import com.example.imemonapi.ConnectionFactory;
import com.example.imemonapi.Model.PokemonAbility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonAbilityDAO {
    Connection db;
    public PokemonAbilityDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
    }

    public ArrayList<PokemonAbility> findByPokemonId(int pokemonId) throws SQLException {
        ArrayList<PokemonAbility> pokemonAbilities = new ArrayList<>();
        String sql = "SELECT * FROM pokemon_ability WHERE pokemon_id = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setInt(1, pokemonId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pokemonAbilities.add(extractPokemonAbility(resultSet));
                }
            }
        }
        return pokemonAbilities;
    }

    private PokemonAbility extractPokemonAbility(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int pokemonId = resultSet.getInt("pokemon_id");
        int abilityId = resultSet.getInt("ability_id");
        return new PokemonAbility(id, pokemonId, abilityId);
    }
}