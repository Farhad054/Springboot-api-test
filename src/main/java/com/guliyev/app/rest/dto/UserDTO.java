package com.guliyev.app.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import com.guliyev.app.rest.models.User;
import com.guliyev.app.rest.models.Course;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull(message = "User Id cannot be null")
    private Long id;

    @NotNull(message = "Firstname must not be null")
    @Size(min = 2, max = 50, message = "Firstname must not be empty")
    private String firstname;

    @NotNull(message = "Lastname must not be null")
    @Size(min = 2, max = 50, message = "Lastname must not be empty")
    private String lastname;

    @NotNull(message = "Age must not be null")
    @Positive(message = "Age must be a positive number")
    private int age;

    @NotNull(message = "Major must not be null")
    @Size(min = 2, max = 50, message = "Major must not be empty")
    private String major;

    @NotNull(message = "University must not be null")
    private UniversityDTO university;

    @NotNull(message = "Courses must not be null")
    private List<CourseDTO> courses;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .age(user.getAge())
                .major(user.getMajor())
                .university(user.getUniversity() != null ? UniversityDTO.builder()
                        .id(user.getUniversity().getId())
                        .name(user.getUniversity().getName())
                        .city(user.getUniversity().getCity())
                        .ranking(user.getUniversity().getRanking())
                        .build() : null)
                .courses(user.getCourses() != null ? user.getCourses().stream()
                        .map(course -> CourseDTO.builder()
                                .id(course.getId())
                                .name(course.getName())
                                .capacity(course.getCapacity())
                                .credits(course.getCredits())
                                .build())
                        .collect(Collectors.toList()) : List.of())
                .build();
    }
}
