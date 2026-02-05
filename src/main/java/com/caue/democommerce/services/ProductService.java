package com.caue.democommerce.services;

import com.caue.democommerce.dto.ProductDTO;
import com.caue.democommerce.entities.Product;
import com.caue.democommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }


}
