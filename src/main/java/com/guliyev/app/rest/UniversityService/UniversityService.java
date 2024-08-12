package com.guliyev.app.rest.UniversityService;

import com.guliyev.app.rest.Models.University;
import com.guliyev.app.rest.dto.UniversityDTO;
import com.guliyev.app.rest.repository.UniversityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guliyev.app.rest.ExceptionHandle.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<UniversityDTO> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        return universities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UniversityDTO> getUniversityById(Long id) {
        Optional<University> university = universityRepository.findById(id);
        return university.map(this::convertToDTO);
    }

    public UniversityDTO saveUniversity(UniversityDTO universityDTO) {
        University university = convertToEntity(universityDTO);
        University savedUniversity = universityRepository.save(university);
        return convertToDTO(savedUniversity);
    }

    public UniversityDTO updateUniversity(Long id, UniversityDTO universityDTO) {
        University universityToUpdate = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found with id: " + id));

        universityToUpdate.setName(universityDTO.getName());
        universityToUpdate.setCity(universityDTO.getCity());
        universityToUpdate.setRanking(universityDTO.getRanking());

        University updatedUniversity = universityRepository.save(universityToUpdate);
        return convertToDTO(updatedUniversity);
    }

    public void deleteUniversity(Long id) {
        University universityToDelete = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found with id: " + id));
        universityRepository.delete(universityToDelete);
    }

    private UniversityDTO convertToDTO(University university) {
        UniversityDTO universityDTO = new UniversityDTO();
        BeanUtils.copyProperties(university, universityDTO);
        universityDTO.setId(university.getId());
        return universityDTO;
    }

    private University convertToEntity(UniversityDTO universityDTO) {
        University university = new University();
        BeanUtils.copyProperties(universityDTO, university);
        university.setId(universityDTO.getId());
        return university;
    }
}
