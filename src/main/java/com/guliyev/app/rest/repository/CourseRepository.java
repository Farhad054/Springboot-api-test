package com.guliyev.app.rest.repository;
import com.guliyev.app.rest.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
}
