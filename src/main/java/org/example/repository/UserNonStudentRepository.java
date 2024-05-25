package org.example.repository;

import org.example.model.User;
import org.example.model.UserNonStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserNonStudentRepository extends JpaRepository<UserNonStudent, Long> {
    Optional<UserNonStudent> findByUserId (Long userId);
}
