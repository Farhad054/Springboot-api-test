package com.guliyev.app.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDTO {
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotNull(message = "University name must not be null")
    @Size(min = 2, max = 50, message = "University name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "City must not be null")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    private String city;

    @NotNull(message = "Ranking must not be null")
    @Positive(message = "Ranking must be a positive number")
    private int ranking;
}
