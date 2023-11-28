package com.bahadir.blogproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserFindAllResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
}
