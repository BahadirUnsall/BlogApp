package com.bahadir.blogproject.mapper;

import com.bahadir.blogproject.dto.request.CategorySaveRequestDto;
import com.bahadir.blogproject.dto.response.CategoryFindAllResponseDto;
import com.bahadir.blogproject.repository.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {
    ICategoryMapper INSTANCE = Mappers.getMapper(ICategoryMapper.class);

    Category categoryRequestDtoToCategory(CategorySaveRequestDto dto);
    CategoryFindAllResponseDto categoryToCategoryResponseDto(Category category);
}
