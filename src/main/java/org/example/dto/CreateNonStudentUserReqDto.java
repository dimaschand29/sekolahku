package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.User;
import org.example.model.UserDetail;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNonStudentUserReqDto implements Serializable {
    private static final long serialVersionUID = -7375011292831764L;

    @NotNull
    private User user;

    @NotNull
    private UserDetail userDetail;

    @NotNull
    private Long roleId;
}
