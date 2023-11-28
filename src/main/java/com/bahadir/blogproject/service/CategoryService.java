package com.bahadir.blogproject.service;

import com.bahadir.blogproject.dto.request.CategorySaveRequestDto;
import com.bahadir.blogproject.dto.response.CategoryFindAllResponseDto;
import com.bahadir.blogproject.exception.BlogException;
import com.bahadir.blogproject.exception.EErrorType;
import com.bahadir.blogproject.mapper.ICategoryMapper;
import com.bahadir.blogproject.repository.ICategoryRepository;
import com.bahadir.blogproject.repository.IPostRepository;
import com.bahadir.blogproject.repository.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ICategoryRepository categoryRepository;
    //private final PostService postService;

    public Optional<Category> findById(Long categoryid) {
        Optional<Category> category = categoryRepository.findById(categoryid);
        if (category.isPresent()){
            return category;
        }
        return Optional.empty();
    }

    public void save(@RequestBody CategorySaveRequestDto dto) {
        categoryRepository.save(ICategoryMapper.INSTANCE.categoryRequestDtoToCategory(dto));
    }

    public List<CategoryFindAllResponseDto> findAll() {
        List<CategoryFindAllResponseDto> list = new ArrayList<>();
        List<Category> all = categoryRepository.findAll();
        if (all.isEmpty()){
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        all.forEach(category -> {
            list.add(ICategoryMapper.INSTANCE.categoryToCategoryResponseDto(category));
        });
        return list;
    }

    public CategoryFindAllResponseDto findCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        CategoryFindAllResponseDto dto = null;
        if (category.isPresent()){
            dto = ICategoryMapper.INSTANCE.categoryToCategoryResponseDto(category.get());
        }else {
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        return dto;
    }


    public void updateById(CategorySaveRequestDto dto, Long categoryid) {
        Optional<Category> category = categoryRepository.findById(categoryid);

        if (category.isPresent()){
            category.get().setCategoryName(dto.getCategoryName());
            category.get().setDescription(dto.getDescription());
        }else{
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        categoryRepository.save(category.get());
    }

    public void deleteById(Long categoryid) {
        Optional<Category> category = categoryRepository.findById(categoryid);
        if (category.isPresent()){
            categoryRepository.deleteById(categoryid);
        }else {
            throw new BlogException(EErrorType.NOT_FOUND);
        }
    }

    public List<CategoryFindAllResponseDto> findByCategoryDescription(String keyword) {
        Category categoryName = categoryRepository.findByDescription(keyword);
        if (categoryName == null){
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        List<CategoryFindAllResponseDto> list = new ArrayList<>();

        CategoryFindAllResponseDto dto = ICategoryMapper.INSTANCE.categoryToCategoryResponseDto(categoryName);
        list.add(dto);
        return list;
    }

}
