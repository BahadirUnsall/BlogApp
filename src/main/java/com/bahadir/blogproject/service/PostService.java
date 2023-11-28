package com.bahadir.blogproject.service;

import com.bahadir.blogproject.dto.request.PostSaveRequestDto;
import com.bahadir.blogproject.dto.response.PostFindAllResponseDto;
import com.bahadir.blogproject.exception.BlogException;
import com.bahadir.blogproject.exception.EErrorType;
import com.bahadir.blogproject.mapper.IPostMapper;
import com.bahadir.blogproject.repository.IPostRepository;
import com.bahadir.blogproject.repository.entity.Category;
import com.bahadir.blogproject.repository.entity.Post;
import com.bahadir.blogproject.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final IPostRepository postRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public void save(PostSaveRequestDto dto, Long userid, Long categoryid) {
        Optional<User> user = userService.findById(userid);
        Optional<Category> category = categoryService.findById(categoryid);
        if (user.isEmpty() || category.isEmpty()){
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        Post post;
        post = IPostMapper.INSTANCE.postSaveRequestDtoToPost(dto);
        post.setUser(user.get());
        post.setCategory(category.get());
        post = postRepository.save(post);
    }

    public List<PostFindAllResponseDto> findAll() {
        List<PostFindAllResponseDto> list = new ArrayList<>();
        List<Post> all = postRepository.findAll();
        if (all.isEmpty()){
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        all.forEach(post -> {
            PostFindAllResponseDto dto = IPostMapper.INSTANCE.postToPostFindAllResponseDto(post);
            dto.setCategoryId(post.getCategory().getId());
            dto.setUserId(post.getUser().getId());
            list.add(dto);
        });
        return list;
    }

    public PostFindAllResponseDto findById(Long postid) {
        Optional<Post> post = postRepository.findById(postid);
        if (post.isEmpty()){
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        PostFindAllResponseDto dto = null;
        if (post.isPresent()){
            dto = IPostMapper.INSTANCE.postToPostFindAllResponseDto(post.get());
            dto.setCategoryId(post.get().getCategory().getId());
            dto.setUserId(post.get().getUser().getId());
        }
        return dto;
    }

    public void updateById(PostSaveRequestDto dto, Long postid) {
        Optional<Post> post = postRepository.findById(postid);
        if (post.isPresent()){
            post.get().setTitle(dto.getTitle());
            post.get().setContent(dto.getContent());
        }else {
            throw new BlogException(EErrorType.NOT_FOUND);
        }

        postRepository.save(post.get());
    }

    public void deleteById(Long postid) {
        Optional<Post> post = postRepository.findById(postid);
        if (post.isPresent()){
            postRepository.deleteById(postid);
        }else{
            throw new BlogException(EErrorType.NOT_FOUND);
        }
    }


    public List<PostFindAllResponseDto> getByUserId(Long userid) {
        Optional<User> user = userService.findById(userid);
        List<PostFindAllResponseDto> list = new ArrayList<>();
        if (user.isPresent()){
            postRepository.findAll().forEach(post -> {
                if (post.getUser().getId() == user.get().getId()){
                    PostFindAllResponseDto dto = IPostMapper.INSTANCE.postToPostFindAllResponseDto(post);
                    dto.setUserId(post.getUser().getId());
                    dto.setCategoryId(post.getCategory().getId());
                    list.add(dto);
                }
            });
        }else{
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        return list;
    }

    public List<PostFindAllResponseDto> getByCategoryId(Long categoryid) {
        Optional<Category> category = categoryService.findById(categoryid);
        List<PostFindAllResponseDto> list = new ArrayList<>();
        if (category.isPresent()){
            postRepository.findAll().forEach(post -> {
                if (post.getCategory().getId() == category.get().getId()){
                    PostFindAllResponseDto dto = IPostMapper.INSTANCE.postToPostFindAllResponseDto(post);
                    dto.setUserId(post.getUser().getId());
                    dto.setCategoryId(post.getCategory().getId());
                    list.add(dto);
                }
            });
        }else {
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        return list;
    }

    public List<PostFindAllResponseDto> findByWord(String word) {
        List<Post> posts = postRepository.findAllByTitleLike(word);
        List<PostFindAllResponseDto> list = new ArrayList<>();
        if (!posts.isEmpty()){
            posts.forEach(post -> {
                PostFindAllResponseDto dto = IPostMapper.INSTANCE.postToPostFindAllResponseDto(post);
                dto.setUserId(post.getUser().getId());
                dto.setCategoryId(post.getCategory().getId());
                list.add(dto);
            });
        }else {
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        return list;
    }

    public List<PostFindAllResponseDto> findByCategory(String value) {
        List<Post> posts = postRepository.findAllByCategoryCategoryNameContainingIgnoreCase(value);
        List<PostFindAllResponseDto> list = new ArrayList<>();
        if (!posts.isEmpty()){
            posts.forEach(post -> {
                PostFindAllResponseDto dto = IPostMapper.INSTANCE.postToPostFindAllResponseDto(post);
                dto.setUserId(post.getUser().getId());
                dto.setCategoryId(post.getCategory().getId());
                list.add(dto);
            });
        }else {
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        return list;
    }

    public List<PostFindAllResponseDto> findByPublishedAt(LocalDate date) {
        List<Post> posts = postRepository.findAllByReleaseDate(date);
        List<PostFindAllResponseDto> list = new ArrayList<>();

        if (!posts.isEmpty()){
            posts.forEach(post -> {
                PostFindAllResponseDto dto = IPostMapper.INSTANCE.postToPostFindAllResponseDto(post);
                dto.setUserId(post.getUser().getId());
                dto.setCategoryId(post.getCategory().getId());
                list.add(dto);
            });
        }else{
            throw new BlogException(EErrorType.NOT_FOUND);
        }
        return list;
    }
}
