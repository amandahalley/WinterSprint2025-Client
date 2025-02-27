package com.keyin.domain;

import java.util.Objects;

public class Aircraft {

    private long id;
    private String type;
    private String airlineName;
    private int numberOfPassengers;

    //default constructor
    public Aircraft() {
    }

    //constructor for type of aircraft
    public Aircraft(String type) {
        this.type = type;
    }

    //constructor initializing all fields of an aircraft
    public Aircraft(long id, String type, String airlineName, int numberOfPassengers) {
        this.id = id;
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    //comparing aircrafts equality from their type
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return id == aircraft.id &&
                numberOfPassengers == aircraft.numberOfPassengers &&
                Objects.equals(type, aircraft.type) &&
                Objects.equals(airlineName, aircraft.airlineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, airlineName, numberOfPassengers);
    }


    //print format
    @Override
    public String toString() {
        return "Aircraft: " + type + " operated by " + airlineName +
                " (Passengers: " + numberOfPassengers + ")";
    }
}
