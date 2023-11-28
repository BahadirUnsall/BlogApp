package com.bahadir.blogproject.controller;

import com.bahadir.blogproject.dto.request.UserSaveRequestDto;
import com.bahadir.blogproject.dto.response.UserFindAllResponseDto;
import com.bahadir.blogproject.exception.BlogException;
import com.bahadir.blogproject.exception.EErrorType;
import com.bahadir.blogproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bahadir.blogproject.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(SAVE)
    public ResponseEntity<String> save(@RequestBody UserSaveRequestDto dto){
        if (dto.getName().trim().isEmpty() || dto.getPassword().trim().isEmpty() || dto.getEmail().trim().isEmpty() || dto.getSurname().trim().isEmpty()){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }
        userService.save(dto);
        return ResponseEntity.ok("Successful");
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<UserFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(userService.findAllResponseDto());
    }
    @GetMapping(FINDBYID +"/{id}")
    public ResponseEntity<UserFindAllResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findByIdResponseDto(id));
    }
    @PutMapping(UPDATEBYID + "/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id, @RequestBody UserSaveRequestDto dto){
        if (dto.getName().trim().isEmpty() || dto.getPassword().trim().isEmpty() || dto.getEmail().trim().isEmpty() || dto.getSurname().trim().isEmpty()){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }
        userService.updateById(id,dto);
        return ResponseEntity.ok("Data Updated..");
    }
    @DeleteMapping(DELETEBYID+"/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("Deleted..");
    }

}
