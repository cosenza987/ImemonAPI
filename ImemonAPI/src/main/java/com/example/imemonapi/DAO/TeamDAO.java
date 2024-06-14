package com.example.imemonapi.DAO;

import com.example.imemonapi.ConnectionFactory;
import com.example.imemonapi.Model.Team;
import com.example.imemonapi.Requests.TeamRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamDAO {
    Connection db;
    public TeamDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
    }

    public ArrayList<Team> findAllByUsername(String username) throws SQLException {
        ArrayList<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM teams WHERE user = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    teams.add(extractTeam(resultSet));
                }
            }
        }
        return teams;
    }

    public Team findById(int teamId) throws SQLException {
        String sql = "SELECT * FROM teams WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setInt(1, teamId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractTeam(resultSet);
                }
            }
        }
        return null;
    }

    public void deleteById(int teamId) throws SQLException {
        String sql = "DELETE FROM teams WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setInt(1, teamId);
            statement.executeUpdate();
        }
    }

    // Helper method to extract Team object from ResultSet
    private Team extractTeam(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("user");
        String teamName = resultSet.getString("team_name");
        int poke1 = resultSet.getInt("poke1");
        int move11 = resultSet.getInt("move11");
        int move12 = resultSet.getInt("move12");
        int move13 = resultSet.getInt("move13");
        int move14 = resultSet.getInt("move14");
        int poke2 = resultSet.getInt("poke2");
        int move21 = resultSet.getInt("move21");
        int move22 = resultSet.getInt("move22");
        int move23 = resultSet.getInt("move23");
        int move24 = resultSet.getInt("move24");
        int poke3 = resultSet.getInt("poke3");
        int move31 = resultSet.getInt("move31");
        int move32 = resultSet.getInt("move32");
        int move33 = resultSet.getInt("move33");
        int move34 = resultSet.getInt("move34");
        int poke4 = resultSet.getInt("poke4");
        int move41 = resultSet.getInt("move41");
        int move42 = resultSet.getInt("move42");
        int move43 = resultSet.getInt("move43");
        int move44 = resultSet.getInt("move44");
        int poke5 = resultSet.getInt("poke5");
        int move51 = resultSet.getInt("move51");
        int move52 = resultSet.getInt("move52");
        int move53 = resultSet.getInt("move53");
        int move54 = resultSet.getInt("move54");
        int poke6 = resultSet.getInt("poke6");
        int move61 = resultSet.getInt("move61");
        int move62 = resultSet.getInt("move62");
        int move63 = resultSet.getInt("move63");
        int move64 = resultSet.getInt("move64");

        return new Team(id, username, teamName, poke1, move11, move12, move13, move14,
                poke2, move21, move22, move23, move24, poke3, move31, move32, move33, move34,
                poke4, move41, move42, move43, move44, poke5, move51, move52, move53, move54,
                poke6, move61, move62, move63, move64);
    }

    // Method to insert a new Team using TeamRequest (assuming TeamRequest matches Team fields)
    public void insertFromTeamRequest(TeamRequest teamRequest) throws SQLException {
        String sql = "INSERT INTO teams (user, team_name, poke1, move11, move12, move13, move14, " +
                "poke2, move21, move22, move23, move24, poke3, move31, move32, move33, move34, " +
                "poke4, move41, move42, move43, move44, poke5, move51, move52, move53, move54, " +
                "poke6, move61, move62, move63, move64) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, teamRequest.getUsername());
            statement.setString(2, teamRequest.getTeamName());
            statement.setInt(3, teamRequest.getPoke1());
            statement.setInt(4, teamRequest.getMove11());
            statement.setInt(5, teamRequest.getMove12());
            statement.setInt(6, teamRequest.getMove13());
            statement.setInt(7, teamRequest.getMove14());
            statement.setInt(8, teamRequest.getPoke2());
            statement.setInt(9, teamRequest.getMove21());
            statement.setInt(10, teamRequest.getMove22());
            statement.setInt(11, teamRequest.getMove23());
            statement.setInt(12, teamRequest.getMove24());
            statement.setInt(13, teamRequest.getPoke3());
            statement.setInt(14, teamRequest.getMove31());
            statement.setInt(15, teamRequest.getMove32());
            statement.setInt(16, teamRequest.getMove33());
            statement.setInt(17, teamRequest.getMove34());
            statement.setInt(18, teamRequest.getPoke4());
            statement.setInt(19, teamRequest.getMove41());
            statement.setInt(20, teamRequest.getMove42());
            statement.setInt(21, teamRequest.getMove43());
            statement.setInt(22, teamRequest.getMove44());
            statement.setInt(23, teamRequest.getPoke5());
            statement.setInt(24, teamRequest.getMove51());
            statement.setInt(25, teamRequest.getMove52());
            statement.setInt(26, teamRequest.getMove53());
            statement.setInt(27, teamRequest.getMove54());
            statement.setInt(28, teamRequest.getPoke6());
            statement.setInt(29, teamRequest.getMove61());
            statement.setInt(30, teamRequest.getMove62());
            statement.setInt(31, teamRequest.getMove63());
            statement.setInt(32, teamRequest.getMove64());

            statement.executeUpdate();
        }
    }
}