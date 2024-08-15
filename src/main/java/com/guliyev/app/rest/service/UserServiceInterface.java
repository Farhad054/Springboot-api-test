package com.guliyev.app.rest.service;

import com.guliyev.app.rest.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(Long id);
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    void addCourseToUser(Long userId, Long courseId);
}
