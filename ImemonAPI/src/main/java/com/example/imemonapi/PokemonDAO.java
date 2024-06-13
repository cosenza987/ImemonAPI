package com.example.imemonapi;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonDAO {
    Connection db;
    public PokemonDAO() throws ClassNotFoundException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        db = connectionFactory.Connect();
        String creation = "CREATE TABLE IF NOT EXISTS pokemon (id int, attack int, defense int, flavor varchar(255), image varchar(255), name varchar(255), sp_att int, sp_def int, speed int, total int, PRIMARY KEY (id));";
        PreparedStatement ps = db.prepareStatement(creation);
        ps.executeUpdate();
    }
    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws SQLException, Throwable {
        try {
            db.close();
        } finally {
            super.finalize();
        }
    }
    public int insert(Pokemon p) throws SQLException {
        String queryString = "INSERT INTO pokemon VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = db.prepareStatement(queryString);
        ps.setInt(1, p.getId());
        ps.setInt(2, p.getAttack());
        ps.setInt(3, p.getDefense());
        ps.setString(4, p.getFlavor());
        ps.setString(5, p.getImage());
        ps.setString(6, p.getName());
        ps.setInt(7, p.getSpAtt());
        ps.setInt(8, p.getSpDef());
        ps.setInt(9, p.getSpeed());
        ps.setInt(10, p.getTotal());
        return ps.executeUpdate();
    }
    public Pokemon search(int id) throws SQLException {
        String queryString = "SELECT * FROM pokemon WHERE id = ?";
        PreparedStatement ps = db.prepareStatement(queryString);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Pokemon p = null;
        while(rs.next()) {
            p = new Pokemon();
            p.setId(rs.getInt("id"));
            p.setAttack(rs.getInt("attack"));
            p.setDefense(rs.getInt("defense"));
            p.setFlavor(rs.getString("flavor"));
            p.setImage(rs.getString("image"));
            p.setName(rs.getString("name"));
            p.setSpAtt(rs.getInt("sp_att"));
            p.setSpDef(rs.getInt("sp_def"));
            p.setSpeed(rs.getInt("speed"));
            p.setTotal(rs.getInt("total"));
        }
        return p;
    }
}
