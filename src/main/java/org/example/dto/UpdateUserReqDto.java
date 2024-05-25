package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserReqDto implements Serializable {
    private static final long serialVersionUID = -7322248194304753045L;

    @NotNull
    private User user;

    @NotNull
    private Integer grade;
}
