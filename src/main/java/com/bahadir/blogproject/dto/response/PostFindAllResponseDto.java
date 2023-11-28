package com.bahadir.blogproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostFindAllResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate releaseDate;
    private Long userId;
    private Long categoryId;
}
