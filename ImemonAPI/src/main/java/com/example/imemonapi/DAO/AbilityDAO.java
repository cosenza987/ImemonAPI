package com.example.imemonapi.DAO;

import com.example.imemonapi.ConnectionFactory;
import com.example.imemonapi.Model.Ability;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbilityDAO {
    Connection db;
    public AbilityDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
    }

    public Ability findById(long id) throws SQLException {
        String sql = "SELECT * FROM ability WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractAbility(resultSet);
                }
            }
        }
        return null;
    }

    private Ability extractAbility(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String effect = resultSet.getString("effect");
        return new Ability(id, name, effect);
    }
}
