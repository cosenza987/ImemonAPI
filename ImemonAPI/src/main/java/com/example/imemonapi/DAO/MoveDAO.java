package com.example.imemonapi.DAO;

import com.example.imemonapi.ConnectionFactory;
import com.example.imemonapi.Model.Move;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveDAO {
    Connection db;
    public MoveDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
    }

    public Move findById(long id) throws SQLException {
        String sql = "SELECT * FROM move WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractMove(resultSet);
                }
            }
        }
        return null;
    }

    private Move extractMove(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String effect = resultSet.getString("effect");
        return new Move(id, name, effect);
    }
}
