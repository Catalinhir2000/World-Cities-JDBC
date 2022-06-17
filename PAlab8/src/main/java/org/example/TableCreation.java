package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreation {
    public void createContinentTable() {
        Connection con = Database.getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS continents( \n" +
                    "ID INT PRIMARY KEY NOT NULL,\n" +
                    "Name VARCHAR(255) NOT NULL\n" +
                    ");";
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createCountryTable() {
        Connection con = Database.getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS countries( \n" +
                    "ID INT PRIMARY KEY NOT NULL,\n" +
                    "Name VARCHAR(255) NOT NULL,\n" +
                    "Code VARCHAR(255) NOT NULL,\n" +
                    "Continent VARCHAR(255) NOT NULL\n" +
                    ");";
            stmt.execute(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createCitiesTable() {
        Connection con = Database.getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS cities( \n" +
                    "ID INT PRIMARY KEY NOT NULL,\n" +
                    "Country VARCHAR(255) NOT NULL,\n" +
                    "Name VARCHAR(255) NOT NULL,\n" +
                    "Capital INT NOT NULL,\n" +
                    "Latitude REAL NOT NULL,\n" +
                    "Longitude REAL NOT NULL\n" +
                    ");";
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
