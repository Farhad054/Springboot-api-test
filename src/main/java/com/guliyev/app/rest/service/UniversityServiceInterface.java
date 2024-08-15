package com.guliyev.app.rest.service;

import com.guliyev.app.rest.dto.UniversityDTO;

import java.util.List;
import java.util.Optional;

public interface UniversityServiceInterface {
    List<UniversityDTO> getAllUniversities();
    Optional<UniversityDTO> getUniversityById(Long id);
    UniversityDTO saveUniversity(UniversityDTO universityDTO);
    UniversityDTO updateUniversity(Long id, UniversityDTO universityDTO);
    void deleteUniversity(Long id);
}
