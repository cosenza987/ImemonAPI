package com.example.imemonapi.DAO;

import com.example.imemonapi.ConnectionFactory;
import com.example.imemonapi.Model.LoggedUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoggedUsersDAO {
    Connection db;
    public LoggedUsersDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
    }

    public void insert(LoggedUsers loggedUsers) throws SQLException {
        String sql = "INSERT INTO logged_users (session, username) VALUES (?, ?)";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, loggedUsers.getSession());
            statement.setString(2, loggedUsers.getUsername());
            statement.executeUpdate();
        }
    }

    public LoggedUsers findBySession(String session) throws SQLException {
        String sql = "SELECT * FROM logged_users WHERE session = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, session);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractLoggedUser(resultSet);
                }
            }
        }
        return null;
    }

    private LoggedUsers extractLoggedUser(ResultSet resultSet) throws SQLException {
        String session = resultSet.getString("session");
        String username = resultSet.getString("username");
        return new LoggedUsers(session, username);
    }
    public LoggedUsers findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM logged_users WHERE username = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractLoggedUser(resultSet);
                }
            }
        }
        return null;
    }
    public void deleteBySession(String session) throws SQLException {
        String sql = "DELETE FROM logged_users WHERE session = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, session);
            statement.executeUpdate();
        }
    }
}
