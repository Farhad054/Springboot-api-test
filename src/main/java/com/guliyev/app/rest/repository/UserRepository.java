package com.guliyev.app.rest.repository;
import com.guliyev.app.rest.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"university", "courses"})
    List<User> findAll();

    @EntityGraph(attributePaths = {"university", "courses"})
    Optional<User> findById(Long id);
}

