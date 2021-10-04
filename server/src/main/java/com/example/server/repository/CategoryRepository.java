package com.example.server.repository;

import com.example.server.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

   Category  getById(Integer id);
    List<Category> findAllByNameContainingIgnoringCase(String name);
}
