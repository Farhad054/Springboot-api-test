package com.guliyev.app.rest.service;

import com.guliyev.app.rest.dto.CourseDTO;

import java.util.List;
import java.util.Optional;

public interface CourseServiceInterface {
    List<CourseDTO> getAllCourses();
    Optional<CourseDTO> getCourseById(Long id);
    CourseDTO saveCourse(CourseDTO courseDTO);
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);
    void deleteCourse(Long id);
}
