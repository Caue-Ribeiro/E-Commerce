package com.caue.democommerce.services;

import com.caue.democommerce.dto.ProductDTO;
import com.caue.democommerce.entities.Product;
import com.caue.democommerce.repositories.ProductRepository;
import com.caue.democommerce.services.exceptions.DatabaseException;
import com.caue.democommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = repository.findAll(pageable);

        return products.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found to be updated.");
        }

        Product entity = repository.getReferenceById(id);
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found.");
        }

        try {
            repository.deleteById(id);
            repository.flush();

        } catch (DataIntegrityViolationException e) {

            throw new DatabaseException("Database integrity violated.");
        }

    }

    private void copyDtoToEntity(Product entity, ProductDTO dto) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());
    }

}
