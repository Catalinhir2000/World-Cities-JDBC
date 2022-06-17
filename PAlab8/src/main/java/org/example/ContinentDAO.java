package org.example;

import org.example.entities.Continent;

import java.sql.*;

public class ContinentDAO {
    public void create(Continent obj) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into continents (ID, name) values (?,?)")) {
            pstmt.setInt(1, obj.getID());
            pstmt.setString(2, obj.getName());
            pstmt.executeUpdate();
        }
    }
    public void view() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(
                    "select * from continents")) {
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2));
                }
            }
        }
    }
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from continents where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "select name from continents where id=?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString(1) : null;
            }
        }
    }
}
