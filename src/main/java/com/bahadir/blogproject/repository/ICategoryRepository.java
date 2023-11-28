package com.bahadir.blogproject.repository;

import com.bahadir.blogproject.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
    Category findByDescription(String keyword);
}
