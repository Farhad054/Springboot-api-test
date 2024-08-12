package com.guliyev.app.rest.UserService;

import com.guliyev.app.rest.Models.User;
import com.guliyev.app.rest.dto.UserDTO;
import com.guliyev.app.rest.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guliyev.app.rest.ExceptionHandle.ResourceNotFoundException;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;
import com.guliyev.app.rest.repository.*;
import com.guliyev.app.rest.Models.*;
// @Data, /@Builder,@NoArgs-all-required-Constructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserService::convertToDTO) // Use static method reference with class name
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserService::convertToDTO); // Use static method reference with class name
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userToUpdate.setFirstname(userDTO.getFirstname());
        userToUpdate.setLastname(userDTO.getLastname());
        userToUpdate.setAge(userDTO.getAge());

        User updatedUser = userRepository.save(userToUpdate);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(userToDelete);
    }

    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        user.getUniversity().getId();
        return  UserDTO.fromEntity(user);

    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setId(userDTO.getId());
        return user;
    }

    @Autowired
    private CourseRepository courseRepository;

    public void addCourseToUser(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));

        if (user.getCourses() == null) {
            user.setCourses(new ArrayList<>());
        }

        user.getCourses().add(course);
        userRepository.save(user);
    }
}
