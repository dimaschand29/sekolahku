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
public class ActivateOrDeactivateUserReqDto implements Serializable {
    private static final long serialVersionUID = 5992337401216619646L;

    @NotNull
    private Long userId;
}
