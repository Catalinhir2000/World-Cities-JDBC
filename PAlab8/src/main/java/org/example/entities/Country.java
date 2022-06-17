package org.example.entities;

public class Country {
    private int ID;
    private String name;
    private String continent;
    private String code;

    public Country(int ID, String name, String continent, String code) {
        this.ID = ID;
        this.name = name;
        this.continent = continent;
        this.code = code;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
