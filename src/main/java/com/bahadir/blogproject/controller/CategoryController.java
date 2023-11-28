package com.bahadir.blogproject.controller;

import com.bahadir.blogproject.dto.request.CategorySaveRequestDto;
import com.bahadir.blogproject.dto.response.CategoryFindAllResponseDto;
import com.bahadir.blogproject.exception.BlogException;
import com.bahadir.blogproject.exception.EErrorType;
import com.bahadir.blogproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bahadir.blogproject.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(SAVE)
    public ResponseEntity<String> save(CategorySaveRequestDto dto){
        if (dto.getCategoryName().trim().isEmpty()|| dto.getDescription().trim().isEmpty()){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }
        categoryService.save(dto);
        return ResponseEntity.ok("Successful");
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<CategoryFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }
    @GetMapping(FINDBYID+"/{categoryid}")
    public ResponseEntity<CategoryFindAllResponseDto> findCategoryById(@PathVariable Long categoryid){
        return ResponseEntity.ok(categoryService.findCategoryById(categoryid));
    }
    @PutMapping(UPDATEBYID+"/{categoryid}")
    public ResponseEntity<String> updateById(@RequestBody CategorySaveRequestDto dto,@PathVariable Long categoryid){
        if (dto.getCategoryName().trim().isEmpty() || dto.getDescription().trim().isEmpty()){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }
        categoryService.updateById(dto,categoryid);
        return ResponseEntity.ok("Updated..");
    }
    @DeleteMapping(DELETEBYID+"/{categoryid}")
    public ResponseEntity<String> deleteById(@PathVariable Long categoryid){
        categoryService.deleteById(categoryid);
        return ResponseEntity.ok("Deleted..");
    }

    //Arama Ve Listeleme Methodları
    @GetMapping(FINDBYCATEGORYDESCRIPTION)
    public ResponseEntity<List<CategoryFindAllResponseDto>> findByCategoryDescription(@RequestParam String keyword){
        if (keyword == null || keyword.trim().isEmpty()){
            throw new BlogException(EErrorType.MISSING_PARAMETER);
        }
        return ResponseEntity.ok(categoryService.findByCategoryDescription(keyword));
    }

}
