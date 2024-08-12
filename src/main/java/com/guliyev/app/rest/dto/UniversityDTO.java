package com.guliyev.app.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
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

    // Constructors
    public UniversityDTO() {
    }

    public UniversityDTO(Long id, String name, String city, int ranking) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.ranking = ranking;
    }
}
