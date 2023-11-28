package com.bahadir.blogproject.repository;

import com.bahadir.blogproject.dto.response.PostFindAllResponseDto;
import com.bahadir.blogproject.repository.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByTitleLike(String word);

    List<Post> findAllByCategoryCategoryNameContainingIgnoreCase(String value);

    @Query("SELECT p FROM Post p WHERE p.releaseDate = :date")
    List<Post> findAllByReleaseDate(@Param("date") LocalDate date);
}
