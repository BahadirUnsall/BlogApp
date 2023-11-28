package com.bahadir.blogproject.service;

import com.bahadir.blogproject.dto.request.UserSaveRequestDto;
import com.bahadir.blogproject.dto.response.UserFindAllResponseDto;
import com.bahadir.blogproject.exception.BlogException;
import com.bahadir.blogproject.exception.EErrorType;
import com.bahadir.blogproject.mapper.IUserMapper;
import com.bahadir.blogproject.repository.IUserRepository;
import com.bahadir.blogproject.repository.entity.User;
import com.bahadir.blogproject.utility.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;

    public void save(UserSaveRequestDto dto) {
        userRepository.save(IUserMapper.INSTANCE.requestDtoToUser(dto));
    }

    public List<UserFindAllResponseDto> findAllResponseDto() {
        List<UserFindAllResponseDto> list = new ArrayList<>();
        List<User> all = userRepository.findAll();
        if (all.isEmpty()){
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        all.forEach(user -> {
            list.add(IUserMapper.INSTANCE.userToReponseDto(user));
        });
        return list;
    }

    public UserFindAllResponseDto findByIdResponseDto(long id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isEmpty()){
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        UserFindAllResponseDto user = IUserMapper.INSTANCE.userToReponseDto(userById.get());
        return user;
    }

    public void updateById(Long id, UserSaveRequestDto updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            user.get().setName(updatedUser.getName());
            user.get().setSurname(updatedUser.getSurname());
            user.get().setEmail(updatedUser.getEmail());
            user.get().setPassword(updatedUser.getPassword());
        }else {
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        userRepository.save(user.get());
    }

    public void deleteById(Long id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()){
            userRepository.delete(userById.get());
        }else {
            throw new BlogException(EErrorType.NOT_FOUND);
        }
    }

    public Optional<User> findById(Long userid) {
        Optional<User> user = userRepository.findById(userid);
        if (user.isPresent()){
            return user;
        }
        return Optional.empty();
    }
}
