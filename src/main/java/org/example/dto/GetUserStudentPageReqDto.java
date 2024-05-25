package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.User;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserStudentPageReqDto implements Serializable {
    private static final long serialVersionUID = -7322248194304753045L;

    @NotNull
    private PageDto page;

    private String username;
    private String city;
    private String province;
    private String fullname;
    private LocalDateTime fromCreateDate;
    private LocalDateTime toCreateDate;
    private Integer grade;
}
