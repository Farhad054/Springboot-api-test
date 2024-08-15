package com.guliyev.app.rest.service;

import com.guliyev.app.rest.dto.UniversityDTO;
import com.guliyev.app.rest.models.University;
import com.guliyev.app.rest.models.User;
import com.guliyev.app.rest.dto.UserDTO;
import com.guliyev.app.rest.repository.UniversityRepository;
import com.guliyev.app.rest.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guliyev.app.rest.exceptionHandle.ResourceNotFoundException;
import com.guliyev.app.rest.dto.CourseDTO;

import java.util.*;
import java.util.stream.Collectors;
import com.guliyev.app.rest.repository.CourseRepository;
import com.guliyev.app.rest.models.Course;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public UserService(UserRepository userRepository, UniversityRepository universityRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserService::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserService::convertToDTO);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userToUpdate.setFirstname(userDTO.getFirstname());
        userToUpdate.setLastname(userDTO.getLastname());
        userToUpdate.setAge(userDTO.getAge());

        if (userDTO.getUniversity() != null) {
            University university = universityRepository.findById(userDTO.getUniversity().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("University not found with id: " + userDTO.getUniversity().getId()));
            userToUpdate.setUniversity(university);
        }

        if (userDTO.getCourses() != null) {
            List<Course> courses = courseRepository.findAllById(userDTO.getCourses().stream()
                    .map(CourseDTO::getId)
                    .collect(Collectors.toList()));
            userToUpdate.setCourses(courses);
        }

        User updatedUser = userRepository.save(userToUpdate);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(userToDelete);
    }

    @Override
    public void addCourseToUser(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        if (user.getCourses() == null) {
            user.setCourses(new ArrayList<>());
        }

        user.getCourses().add(course);
        userRepository.save(user);
    }

    private static UserDTO convertToDTO(User user) {
        return UserDTO.fromEntity(user);
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setId(userDTO.getId());
        return user;
    }
}
