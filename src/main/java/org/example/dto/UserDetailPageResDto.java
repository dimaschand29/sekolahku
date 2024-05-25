package org.example.dto;

import java.time.LocalDateTime;

public interface UserDetailPageResDto {
    String getUsername();
    String getEmail();
    String getAddress();
    String getCity();
    String getProvince();
    String getFullName();
    LocalDateTime getCreateDate();
    Integer getGrade();
}
