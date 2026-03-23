package com.caue.democommerce.services;

import com.caue.democommerce.dto.CategoryDTO;
import com.caue.democommerce.entities.Category;
import com.caue.democommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository service;


    public CategoryService(CategoryRepository service) {
        this.service = service;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = service.findAll();

       return categories.stream().map(CategoryDTO::new).toList();
    }
}
