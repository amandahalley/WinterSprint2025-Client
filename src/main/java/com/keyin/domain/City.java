package com.keyin.domain;

import java.util.List;
import java.util.Objects;

public class City {

    private Long id;
    private String name;
    private String state;
    private int population;
    private List<Airport> airports;

    //constructors
    public City() {
    }

    public City(Long id, String name, String state, int population, List<Airport> airports) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.population = population;
        this.airports = airports;
    }

    //getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(name, city.name) &&
                Objects.equals(state, city.state);
    }

    //print format
    @Override
    public String toString() {
        StringBuilder airportNames = new StringBuilder();
        if (airports != null && !airports.isEmpty()) {
            for (Airport airport : airports) {
                airportNames.append(airport.getName()).append(", ");
            }
        }
        return "City ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "State: " + state + "\n" +
                "Population: " + population + "\n" +
                "Airports: " + (airportNames.length() > 0 ? airportNames.substring(0, airportNames.length() - 2) : "None") + "\n";
    }
}