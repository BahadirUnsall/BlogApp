package com.bahadir.blogproject.mapper;

import com.bahadir.blogproject.dto.request.PostSaveRequestDto;
import com.bahadir.blogproject.dto.response.PostFindAllResponseDto;
import com.bahadir.blogproject.repository.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPostMapper {
    IPostMapper INSTANCE = Mappers.getMapper(IPostMapper.class);

    Post postSaveRequestDtoToPost(PostSaveRequestDto dto);

    PostFindAllResponseDto postToPostFindAllResponseDto(Post post);
}
