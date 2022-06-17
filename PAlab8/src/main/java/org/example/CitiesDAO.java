package org.example;

import org.example.entities.City;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class CitiesDAO {
    public void create(City obj) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into cities (ID, Country, Name, Capital, Latitude, Longitude) values (?, ?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, obj.getId());
            pstmt.setString(2, obj.getCountry());
            pstmt.setString(3, obj.getName());
            pstmt.setInt(4, obj.getCapital());
            pstmt.setDouble(5, obj.getLat());
            pstmt.setDouble(6, obj.getLon());
            pstmt.executeUpdate();
        }
    }

    public double findLatitudeByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from cities where name='" + name + "'")) {
            return rs.next() ? rs.getDouble(5) : null;
        }
    }

    public double findLongitudeByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from cities where name='" + name + "'")) {
            return rs.next() ? rs.getDouble(6) : null;
        }
    }

    public Double calculateDistance(String city1, String city2) throws SQLException {
        Double lat1 = findLatitudeByName(city1);
        Double lat2 = findLatitudeByName(city2);
        Double lon1 = findLongitudeByName(city1);
        Double lon2 = findLongitudeByName(city2);
        return distance(lat1, lat2, lon1, lon2);
    }



    public void importCSV(){
        BufferedReader br = null;
        try {
            String row;
            br = new BufferedReader(new java.io.FileReader("src/main/resources/concap.csv"));
            int id = 0;
            while ((row = br.readLine()) != null) {
                String[] values = row.split(",");
                try {
                    City c = new City(id, values[0], values[1], 1, Double.parseDouble(values[2]), Double.parseDouble(values[3]));
                    this.create(c);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                id++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static double distance(double lat1, double lat2, double lon1, double lon2) {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }
}
