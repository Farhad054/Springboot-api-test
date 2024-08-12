package com.guliyev.app.rest.repository;
import com.guliyev.app.rest.Models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    @EntityGraph(attributePaths = {"users"})
    List<University> findAll();

    @EntityGraph(attributePaths = {"users"})
    Optional<University> findById(Long id);
}
