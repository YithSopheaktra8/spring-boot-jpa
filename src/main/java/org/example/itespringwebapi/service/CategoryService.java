package org.example.itespringwebapi.service;

import org.example.itespringwebapi.dto.CategoryCreateRequest;
import org.example.itespringwebapi.dto.CategoryEditRequest;
import org.example.itespringwebapi.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findCategories();

    CategoryResponse findCategoryById(Integer id);

    CategoryResponse findCategoryByName(String name);

    void createNewCategory(CategoryCreateRequest request);

    CategoryResponse editCategoryById(Integer id, CategoryEditRequest categoryRequest);

    void deleteCategoryById(Integer id);
}
