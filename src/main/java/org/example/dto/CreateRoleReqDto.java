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
public class CreateRoleReqDto implements Serializable {
    private static final long serialVersionUID = -7322248194304753045L;

    @NotNull
    private String name;

    @NotNull
    private Boolean isStudent;
}
