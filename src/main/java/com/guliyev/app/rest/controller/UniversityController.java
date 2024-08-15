package com.guliyev.app.rest.controller;

import com.guliyev.app.rest.dto.UniversityDTO;
import com.guliyev.app.rest.service.UniversityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final UniversityServiceInterface universityService;

    @Autowired
    public UniversityController(UniversityServiceInterface universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public List<UniversityDTO> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityDTO> getUniversityById(@PathVariable Long id) {
        Optional<UniversityDTO> universityDTO = universityService.getUniversityById(id);
        return universityDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UniversityDTO createUniversity(@RequestBody UniversityDTO universityDTO) {
        return universityService.saveUniversity(universityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityDTO> updateUniversity(@PathVariable Long id, @RequestBody UniversityDTO universityDTO) {
        UniversityDTO updatedUniversity = universityService.updateUniversity(id, universityDTO);
        return ResponseEntity.ok(updatedUniversity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }
}
