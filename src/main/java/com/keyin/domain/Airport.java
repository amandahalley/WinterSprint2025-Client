package com.keyin.domain;

import java.util.Objects;

public class Airport {

    private long id;
    private String name;
    private String code;

    //default constructor
    public Airport() {
    }

    //constructor for code
    public Airport(String code) {
        this.code = code;
    }

    //constructor initializing all fields of airport
    public Airport(long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //equals method checking all fields (id, name, and code)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return id == airport.id &&
                Objects.equals(name, airport.name) &&
                Objects.equals(code, airport.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    @Override
    public String toString() {
        return "Airport: " + name + " (" + code + ")";
    }
}

