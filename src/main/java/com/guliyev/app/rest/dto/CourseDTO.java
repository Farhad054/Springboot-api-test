package com.guliyev.app.rest.dto;

public class CourseDTO {
    private String name;
    private int capacity;
    private int credits;

    // Constructors
    public CourseDTO() {
    }

    public CourseDTO(String name, int capacity, int credits) {
        this.name = name;
        this.capacity = capacity;
        this.credits = credits;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
