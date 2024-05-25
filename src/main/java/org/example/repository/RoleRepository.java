package org.example.repository;

import org.example.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName (String name);

    Optional<Role> findByIdAndIsStudent (Long id, Boolean IsStudent);
}
