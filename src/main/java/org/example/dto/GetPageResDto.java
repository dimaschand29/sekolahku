package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPageResDto implements Serializable {
    private static final long serialVersionUID = -1803027508599554110L;
    private List<?> contentList;
    private PageDto page;
}
