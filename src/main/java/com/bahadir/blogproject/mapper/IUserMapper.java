package com.bahadir.blogproject.mapper;

import com.bahadir.blogproject.dto.request.UserSaveRequestDto;
import com.bahadir.blogproject.dto.response.UserFindAllResponseDto;
import com.bahadir.blogproject.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User requestDtoToUser(UserSaveRequestDto dto);

    UserFindAllResponseDto userToReponseDto(User user);
}
