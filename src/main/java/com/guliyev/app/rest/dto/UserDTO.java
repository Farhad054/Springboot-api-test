package com.guliyev.app.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import com.guliyev.app.rest.Models.User;
import com.guliyev.app.rest.Models.Course;
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

    @NotNull(message = "University ID must not be null")
    private Long universityId;

    @NotNull(message = "course IDs must not be null")
    private List<Long> courseIds;

    @NotNull(message = "Course names must not be null")
    private List<String> courseNames;

    public static UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setAge(user.getAge());
        userDTO.setMajor(user.getMajor());
        if (user.getUniversity() != null) {
            userDTO.setUniversityId(user.getUniversity().getId());
        }
        if (user.getCourses() != null) {
            userDTO.setCourseIds(user.getCourses().stream()
                    .map(Course::getId)
                    .collect(Collectors.toList()));

            userDTO.setCourseNames(user.getCourses().stream()
                    .map(Course::getName)
                    .collect(Collectors.toList()));
        }

        return userDTO;
    }
}
