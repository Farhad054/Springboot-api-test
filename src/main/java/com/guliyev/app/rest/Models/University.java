package com.guliyev.app.rest.Models;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;
@Entity
public class University {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String city;
    @Column
    private int ranking;

    //relationships

    @OneToMany(mappedBy = "university")
    private List<User> users;

    @OneToMany(mappedBy = "university")
    private List<Course> courses;

    //getters and setters

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
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
