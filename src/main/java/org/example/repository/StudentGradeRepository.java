package org.example.repository;

import org.example.model.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {
    Optional<StudentGrade> findByGrade(Integer grade);

    @Query(value = "select sg from StudentGrade sg " +
            "join Role r on r.id = sg.roleId " +
            "where r.isDel = false")
    List<StudentGrade> findAllStudentGrade();
}
