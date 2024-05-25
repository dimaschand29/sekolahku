package org.example.repository;

import org.example.model.UserStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStudentRepository extends JpaRepository<UserStudent, Long> {
    Optional<UserStudent> findByUserId (Long userId);
}
