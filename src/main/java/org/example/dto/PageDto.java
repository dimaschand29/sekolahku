package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDto implements Serializable {
    private Integer pageNo;
    private Integer pageSize;
    private Long totalRecord;
    private Integer totalPage;
}
