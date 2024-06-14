package com.example.imemonapi.DAO;

import com.example.imemonapi.ConnectionFactory;
import com.example.imemonapi.Model.User;

import java.sql.*;

public class UserDAO {
    Connection db;
    public UserDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
        String creation = "CREATE TABLE IF NOT EXISTS users (id int, email varchar(255), password varchar(255), username varchar(255), PRIMARY KEY (id));";
        Statement statement = db.createStatement();
        statement.executeUpdate(creation);
    }

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO users (email, password, username) VALUES (?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUsername());
            statement.executeUpdate();
        }
    }

    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUser(resultSet);
                }
            }
        }
        return null;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String username = resultSet.getString("username");
        return new User(id, email, password, username);
    }
}
