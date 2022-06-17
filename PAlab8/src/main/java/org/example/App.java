package org.example;

import org.example.entities.Continent;
import org.example.entities.Country;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            var creator  = new TableCreation();
            creator.createContinentTable();
            creator.createCountryTable();
            creator. createCitiesTable();

            var continents = new ContinentDAO();
            Continent c1 = new Continent("Africa", 2);
//            continents.create(c1);
//            continents.create("Europe", 1);
            Database.getConnection().commit();
            var countries = new CountryDAO();
            int europeId = continents.findByName("Europe");
            Country cou1 = new Country(3, "France", "Europe", "fr");
//            countries.create(cou1);
//            countries.create("Romania", 1, "ro", "europa");
//            countries.create("Ukraine", 2, "ua", "europa");
            continents.view();
            countries.view();
            var cities = new CitiesDAO();
//            cities.importCSV();
            System.out.println(cities.calculateDistance("Hargeisa", "Yaren"));

            Database.getConnection().commit();
            //TODO: print all the countries in Europe

            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }

    }
}
