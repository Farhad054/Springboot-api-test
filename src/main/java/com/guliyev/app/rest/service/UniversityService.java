package com.guliyev.app.rest.service;

import com.guliyev.app.rest.models.University;
import com.guliyev.app.rest.dto.UniversityDTO;
import com.guliyev.app.rest.repository.UniversityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guliyev.app.rest.exceptionHandle.ResourceNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UniversityService implements UniversityServiceInterface {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public List<UniversityDTO> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        return universities.stream()
                .map(UniversityService::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UniversityDTO> getUniversityById(Long id) {
        Optional<University> university = universityRepository.findById(id);
        return university.map(UniversityService::convertToDTO);
    }

    @Override
    public UniversityDTO saveUniversity(UniversityDTO universityDTO) {
        University university = convertToEntity(universityDTO);
        University savedUniversity = universityRepository.save(university);
        return convertToDTO(savedUniversity);
    }

    @Override
    public UniversityDTO updateUniversity(Long id, UniversityDTO universityDTO) {
        University universityToUpdate = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found with id: " + id));

        universityToUpdate.setName(universityDTO.getName());
        universityToUpdate.setCity(universityDTO.getCity());
        universityToUpdate.setRanking(universityDTO.getRanking());

        University updatedUniversity = universityRepository.save(universityToUpdate);
        return convertToDTO(updatedUniversity);
    }

    @Override
    public void deleteUniversity(Long id) {
        University universityToDelete = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found with id: " + id));
        universityRepository.delete(universityToDelete);
    }

    private static UniversityDTO convertToDTO(University university) {
        UniversityDTO universityDTO = new UniversityDTO();
        universityDTO.setId(university.getId());
        universityDTO.setName(university.getName());
        universityDTO.setCity(university.getCity());
        universityDTO.setRanking(university.getRanking());
        return universityDTO;
    }

    private University convertToEntity(UniversityDTO universityDTO) {
        University university = new University();
        BeanUtils.copyProperties(universityDTO, university);
        return university;
    }
}
