package com.guliyev.app.rest.Controller;

import com.guliyev.app.rest.ExceptionHandle.ApiRequestException;
import com.guliyev.app.rest.UserService.UserService;
import com.guliyev.app.rest.UniversityService.UniversityService;
import com.guliyev.app.rest.CourseService.CourseService;
import com.guliyev.app.rest.dto.UserDTO;
import com.guliyev.app.rest.dto.UniversityDTO;
import com.guliyev.app.rest.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import com.guliyev.app.rest.ExceptionHandle.GlobalExceptionHandler;
import com.guliyev.app.rest.ExceptionHandle.ApiRequestException;
import com.guliyev.app.rest.ExceptionHandle.ResourceNotFoundException;
import com.guliyev.app.rest.ExceptionHandle.ErrorResponse;
import com.guliyev.app.rest.ExceptionHandle.InvalidInputException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api") // Add this to define a common base path
public class ApiControllers {

    private final UserService userService;
    private final UniversityService universityService;
    private final CourseService courseService;

    @Autowired
    public ApiControllers(UserService userService, UniversityService universityService, CourseService courseService) {
        this.userService = userService;
        this.universityService = universityService;
        this.courseService = courseService;
    }

    //User control

    @GetMapping(value = "/users")
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/users/{id}")
    public UserDTO getUserById(@PathVariable long id) {
        return userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @PostMapping(value = "/users")
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.saveUser(userDTO);
    }

    @PutMapping(value = "/users/{id}")
    public UserDTO updateUser(@PathVariable long id, @Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "Deleted user with id: " + id;
    }

    //University control

    @GetMapping(value = "/universities")
    public List<UniversityDTO> getUniversities(){
        return universityService.getAllUniversities();
    }

    @GetMapping(value = "/universities/{id}")
    public UniversityDTO getUniversityById(@PathVariable long id) {
        return universityService.getUniversityById(id).orElseThrow(() -> new ResourceNotFoundException("University not found with id " + id));
    }

    @PostMapping(value = "/universities")
    public UniversityDTO saveUniversity(@Valid @RequestBody UniversityDTO universityDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldError().getDefaultMessage());
        }
        return universityService.saveUniversity(universityDTO);
    }

    @PutMapping(value = "/universities/{id}")
    public UniversityDTO updateUniversity(@PathVariable long id, @Valid @RequestBody UniversityDTO universityDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldError().getDefaultMessage());
        }
        return universityService.updateUniversity(id, universityDTO);
    }

    @DeleteMapping(value = "/universities/{id}")
    public String deleteUniversity(@PathVariable long id){
        universityService.deleteUniversity(id);
        return "Deleted university with id: " + id;
    }

    //Course control

    @GetMapping(value = "/courses")
    public List<CourseDTO> getCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping(value = "/courses/{id}")
    public CourseDTO getCourseById(@PathVariable long id) {
        return courseService.getCourseById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    @PostMapping(value = "/courses")
    public CourseDTO saveCourse(@Valid @RequestBody CourseDTO courseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldError().getDefaultMessage());
        }
        return courseService.saveCourse(courseDTO);
    }

    @PutMapping(value = "/courses/{id}")
    public CourseDTO updateCourse(@PathVariable long id, @Valid @RequestBody CourseDTO courseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldError().getDefaultMessage());
        }
        return courseService.updateCourse(id, courseDTO);
    }

    @DeleteMapping(value = "/courses/{id}")
    public String deleteCourse(@PathVariable long id){
        courseService.deleteCourse(id);
        return "Deleted course with id: " + id;
    }

    // Add course to user
    @PostMapping("/users/{userId}/courses/{courseId}")
    public ResponseEntity<String> addCourseToUser(@PathVariable Long userId, @PathVariable Long courseId) {
        userService.addCourseToUser(userId, courseId);
        return ResponseEntity.ok("Course added to user successfully.");
    }
}
