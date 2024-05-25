package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteRoleReqDto implements Serializable {
    private static final long serialVersionUID = -7322248194304753045L;

    @NotNull
    private Long roleId;
}
