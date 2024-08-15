package com.guliyev.app.rest.service;

import com.guliyev.app.rest.models.Course;
import com.guliyev.app.rest.dto.CourseDTO;
import com.guliyev.app.rest.repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guliyev.app.rest.exceptionHandle.ResourceNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService implements CourseServiceInterface {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(CourseService::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDTO> getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(CourseService::convertToDTO);
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course courseToUpdate = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        courseToUpdate.setName(courseDTO.getName());
        courseToUpdate.setCapacity(courseDTO.getCapacity());
        courseToUpdate.setCredits(courseDTO.getCredits());

        Course updatedCourse = courseRepository.save(courseToUpdate);
        return convertToDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        Course courseToDelete = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        courseRepository.delete(courseToDelete);
    }

    private static CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setCapacity(course.getCapacity());
        courseDTO.setCredits(course.getCredits());
        return courseDTO;
    }

    private Course convertToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        return course;
    }
}
