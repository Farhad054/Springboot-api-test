package com.guliyev.app.rest.repository;
import com.guliyev.app.rest.Models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UniversityRepository extends JpaRepository<University, Long>{
}
