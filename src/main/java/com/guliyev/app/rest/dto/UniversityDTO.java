package com.guliyev.app.rest.dto;

public class UniversityDTO {
    private String name;
    private String city;
    private int ranking;

    // Constructors
    public UniversityDTO() {
    }

    public UniversityDTO(String name, String city, int ranking) {
        this.name = name;
        this.city = city;
        this.ranking = ranking;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
