package com.guliyev.app.rest.CourseService;

import com.guliyev.app.rest.Models.Course;
import com.guliyev.app.rest.dto.CourseDTO;
import com.guliyev.app.rest.repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CourseDTO> getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(this::convertToDTO);
    }

    public CourseDTO saveCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course courseToUpdate = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        courseToUpdate.setName(courseDTO.getName());
        courseToUpdate.setCapacity(courseDTO.getCapacity());
        courseToUpdate.setCredits(courseDTO.getCredits());

        Course updatedCourse = courseRepository.save(courseToUpdate);
        return convertToDTO(updatedCourse);
    }

    public void deleteCourse(Long id) {
        Course courseToDelete = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        courseRepository.delete(courseToDelete);
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        return courseDTO;
    }

    private Course convertToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        return course;
    }
}
