package org.example.itespringwebapi.repository;

import org.example.itespringwebapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
}
