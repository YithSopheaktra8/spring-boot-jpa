package org.example.itespringwebapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.itespringwebapi.dto.CategoryCreateRequest;
import org.example.itespringwebapi.dto.CategoryEditRequest;
import org.example.itespringwebapi.dto.CategoryResponse;
import org.example.itespringwebapi.model.Category;
import org.example.itespringwebapi.repository.CategoryRepository;
import org.example.itespringwebapi.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> findCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> new CategoryResponse(
                        category.getName(),
                        category.getDescription()
                )).toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "category has not been found!"
                ));
        return new CategoryResponse(category.getName(),category.getDescription());
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        return null;
    }

    @Override
    public void createNewCategory(CategoryCreateRequest request ) {
        if(categoryRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category already existed"
            ) ;
        }
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse editCategoryById(Integer id, CategoryEditRequest categoryRequest) {
        if(!categoryRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found!"
            );
        }
        Category category = new Category();
        category.setId(id);
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        categoryRepository.save(category);
        return this.findCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        if(!categoryRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found!"
            );
        }
        categoryRepository.deleteById(id);
    }
}
