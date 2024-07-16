package com.guliyev.app.rest.Controller;

import com.guliyev.app.rest.UserService.UserService;
import com.guliyev.app.rest.UniversityService.UniversityService;
import com.guliyev.app.rest.CourseService.CourseService;
import com.guliyev.app.rest.dto.UserDTO;
import com.guliyev.app.rest.dto.UniversityDTO;
import com.guliyev.app.rest.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
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

    @PostMapping(value = "/usersave")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }
//
    @PutMapping(value = "/userupdate/{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO userDTO){
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping(value = "/userdelete/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return "Deleted user with id: " + id;
    }

    //University control

    @GetMapping(value = "/universities")
    public List<UniversityDTO> getUniversities(){
        return universityService.getAllUniversities();
    }

    @PostMapping(value = "/universitysave")
    public UniversityDTO saveUniversity(@RequestBody UniversityDTO universityDTO){
        return universityService.saveUniversity(universityDTO);
    }

    @PutMapping(value = "/universityupdate/{id}")
    public UniversityDTO updateUniversity(@PathVariable long id, @RequestBody UniversityDTO universityDTO){
        return universityService.updateUniversity(id, universityDTO);
    }

    @DeleteMapping(value = "/universitydelete/{id}")
    public String deleteUniversity(@PathVariable long id){
        universityService.deleteUniversity(id);
        return "Deleted university with id: " + id;
    }

    //Course control

    @GetMapping(value = "/courses")
    public List<CourseDTO> getCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping(value = "/coursesave")
    public CourseDTO saveCourse(@RequestBody CourseDTO courseDTO){
        return courseService.saveCourse(courseDTO);
    }

    @PutMapping(value = "/courseupdate/{id}")
    public CourseDTO updateCourse(@PathVariable long id, @RequestBody CourseDTO courseDTO){
        return courseService.updateCourse(id, courseDTO);
    }

    @DeleteMapping(value = "/coursedalete/{id}")
    public String deleteCourse(@PathVariable long id){
        courseService.deleteCourse(id);
        return "Deleted course with id: " + id;
    }
}
