package com.guliyev.app.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotNull(message = "Course name must not be null")
    @Size(min = 2, max = 50, message = "Course name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Capacity must not be null")
    @Positive(message = "Capacity must be a positive number")
    private int capacity;

    @NotNull(message = "Credits must not be null")
    @Positive(message = "Credits must be a positive number")
    private int credits;


}
