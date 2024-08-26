package com.guliyev.app.rest.models;

import javax.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private int age;

    @Column
    private String major;

    @Column(unique = true)
    private String email;

    private String password;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToMany
    @JoinTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
