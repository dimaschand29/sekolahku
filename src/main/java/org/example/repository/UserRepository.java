package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByUsername (String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isDel = :isDel WHERE u.id = :id")
    void activateOrDeactivateUser(@Param("isDel") Boolean isDel, @Param("id") Long id);
}
