package com.bahadir.blogproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryFindAllResponseDto {
    private Long id;
    private String categoryName;
    private String description;
}
