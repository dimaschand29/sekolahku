package org.example.repository;

import org.example.dto.UserDetailPageResDto;
import org.example.model.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    @Query(value = "SELECT u.username, ud.email, ud.address, ud.city, ud.province, ud.fullname AS fullName, u.create_date AS createDate, sg.grade AS grade " +
            "FROM user u " +
            "JOIN user_detail ud ON u.id = ud.user_id " +
            "JOIN user_student us ON u.id = us.user_id " +
            "JOIN student_grade sg ON sg.id = us.student_grade_id " +
            "WHERE " +
            "(:username IS NULL OR u.username LIKE CONCAT('%', :username, '%')) AND " +
            "(:city IS NULL OR ud.city LIKE CONCAT('%', :city, '%')) AND " +
            "(:province IS NULL OR ud.province LIKE CONCAT('%', :province, '%')) AND " +
            "(:fullName IS NULL OR ud.fullname LIKE CONCAT('%', :fullName, '%')) AND " +
            "(:startDate IS NULL OR u.create_date >= :startDate) AND " +
            "(:endDate IS NULL OR u.create_date <= :endDate) AND " +
            "(:isDel IS NULL OR u.is_del = :isDel) AND " +
            "(:grade IS NULL OR sg.grade = :grade) " +
            "ORDER BY u.create_date DESC",
            countQuery = "SELECT COUNT(*) " +
                    "FROM user u " +
                    "JOIN user_detail ud ON u.id = ud.user_id " +
                    "JOIN user_student us ON u.id = us.user_id " +
                    "JOIN student_grade sg ON sg.id = us.student_grade_id " +
                    "WHERE " +
                    "(:username IS NULL OR u.username LIKE CONCAT('%', :username, '%')) AND " +
                    "(:city IS NULL OR ud.city LIKE CONCAT('%', :city, '%')) AND " +
                    "(:province IS NULL OR ud.province LIKE CONCAT('%', :province, '%')) AND " +
                    "(:fullName IS NULL OR ud.fullname LIKE CONCAT('%', :fullName, '%')) AND " +
                    "(:startDate IS NULL OR u.create_date >= :startDate) AND " +
                    "(:endDate IS NULL OR u.create_date <= :endDate) AND " +
                    "(:isDel IS NULL OR u.is_del = :isDel) AND " +
                    "(:grade IS NULL OR sg.grade = :grade) ",
            nativeQuery = true)
    Page<UserDetailPageResDto> findAllStudentFilteredPage(@Param("username") String username,
                                                          @Param("city") String city,
                                                          @Param("province") String province,
                                                          @Param("fullName") String fullName,
                                                          @Param("startDate") LocalDateTime startDate,
                                                          @Param("endDate") LocalDateTime endDate,
                                                          @Param("isDel") Boolean isDel,
                                                          @Param("grade") Integer grade,
                                                          Pageable pageable);


}
