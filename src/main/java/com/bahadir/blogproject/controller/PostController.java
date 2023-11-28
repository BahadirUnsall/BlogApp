package com.bahadir.blogproject.controller;

import com.bahadir.blogproject.dto.request.PostSaveRequestDto;
import com.bahadir.blogproject.dto.response.CategoryFindAllResponseDto;
import com.bahadir.blogproject.dto.response.PostFindAllResponseDto;
import com.bahadir.blogproject.dto.response.UserFindAllResponseDto;
import com.bahadir.blogproject.exception.BlogException;
import com.bahadir.blogproject.exception.EErrorType;
import com.bahadir.blogproject.repository.entity.Post;
import com.bahadir.blogproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.bahadir.blogproject.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CategoryController categoryController;
    private final UserController userController;

    @PostMapping(SAVE + "/{userid}/{categoryid}")
    public ResponseEntity<String> save(@RequestBody PostSaveRequestDto dto, @PathVariable Long userid, @PathVariable Long categoryid){
        if (userid == null || categoryid == null){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }
        if (dto.getContent().trim().isEmpty() || dto.getTitle().trim().isEmpty()){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }

        postService.save(dto,userid,categoryid);
        return ResponseEntity.ok("Successful");
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<PostFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping(FINDBYID + "/{postid}")
    public ResponseEntity<PostFindAllResponseDto> findById(@PathVariable Long postid){
        return ResponseEntity.ok(postService.findById(postid));
    }
    @PutMapping(UPDATEBYID+"/{postid}")
    public ResponseEntity<String> updateById(@RequestBody PostSaveRequestDto dto, @PathVariable Long postid){
        if (dto.getTitle().trim().isEmpty() || dto.getContent().trim().isEmpty()){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }
        postService.updateById(dto,postid);
        return ResponseEntity.ok("Updated..");
    }
    @DeleteMapping(DELETEBYID+"/{postid}")
    public ResponseEntity<String> deleteById(@PathVariable Long postid){
        postService.deleteById(postid);
        return ResponseEntity.ok("Deleted..");
    }

    @GetMapping(GETBYCATEGORYID+"/{categoryid}")
    public ResponseEntity<List<PostFindAllResponseDto>> getByCategoryId(@PathVariable Long categoryid){
        return ResponseEntity.ok(postService.getByCategoryId(categoryid));
    }

    //Arama ve Listeleme Methodları
    @GetMapping(GETBYUSERID+"/{userid}" )
    public ResponseEntity<List<PostFindAllResponseDto>> getByUserId(@PathVariable Long userid){
        return ResponseEntity.ok(postService.getByUserId(userid));
    }
    @GetMapping(FINDBYWORD+"/{word}")
    public ResponseEntity<List<PostFindAllResponseDto>> findByWord(@PathVariable String word){
        if (word.trim().isEmpty()){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }
        return ResponseEntity.ok(postService.findByWord("%"+word+"%"));
    }
    @GetMapping(FINDBYCATEGORY+"/{value}")
    public ResponseEntity<List<PostFindAllResponseDto>> findByCategory(@PathVariable String value){
        if (value.trim().isEmpty()){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }
        return ResponseEntity.ok(postService.findByCategory(value));
    }
    @GetMapping(FINDBYPUBLISHED_AT+"/{yyyyMMdd}")
    public ResponseEntity<List<PostFindAllResponseDto>> findByPublishedAt(@PathVariable String yyyyMMdd){
        if (!isValidDateFormat(yyyyMMdd)){
            throw new BlogException(EErrorType.WRONG_FORMAT);
        }
        LocalDate date = LocalDate.parse(yyyyMMdd);
        return ResponseEntity.ok(postService.findByPublishedAt(date));
    }
    private boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
