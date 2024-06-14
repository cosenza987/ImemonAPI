package com.example.imemonapi.DAO;

import com.example.imemonapi.ConnectionFactory;
import com.example.imemonapi.Model.PokemonMove;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonMoveDAO {
    Connection db;
    public PokemonMoveDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
    }

    public ArrayList<PokemonMove> findByPokemonId(long pokemonId) throws SQLException {
        ArrayList<PokemonMove> pokemonMoves = new ArrayList<>();
        String sql = "SELECT * FROM pokemon_move WHERE pokemon_id = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setLong(1, pokemonId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pokemonMoves.add(extractPokemonMove(resultSet));
                }
            }
        }
        return pokemonMoves;
    }

    private PokemonMove extractPokemonMove(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int pokemonId = resultSet.getInt("pokemon_id");
        int moveId = resultSet.getInt("move_id");
        return new PokemonMove(id, pokemonId, moveId);
    }
}