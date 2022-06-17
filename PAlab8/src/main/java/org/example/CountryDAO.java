package org.example;

import org.example.entities.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CountryDAO {
    public void create(Country obj) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (ID, name, code, continent) values (?, ?, ?, ?)")) {
            pstmt.setInt(1, obj.getID());
            pstmt.setString(2, obj.getName());
            pstmt.setString(3, obj.getCode());
            pstmt.setString(4, obj.getContinent());
            pstmt.executeUpdate();
        }
    }
    public void view() throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "select * from countries")) {
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                }
            }
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "select ID from countries where name = ?")) {
            pstmt.setString(1, name);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return null;
    }
    public String findByID(int ID) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "select name from countries where ID = ?")) {
            pstmt.setInt(1, ID);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        return null;
    }
}
