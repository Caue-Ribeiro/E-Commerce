package com.caue.democommerce.controllers;


import com.caue.democommerce.dto.CategoryDTO;
import com.caue.democommerce.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService service;


    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){

        List<CategoryDTO> categories = service.getAllCategories();

        return ResponseEntity.ok(categories);
    }
}
