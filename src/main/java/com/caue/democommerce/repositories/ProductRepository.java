package com.caue.democommerce.repositories;

import com.caue.democommerce.dto.ProductDTO;
import com.caue.democommerce.dto.ProductMinDTO;
import com.caue.democommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT new com.caue.democommerce.dto.ProductMinDTO(obj) FROM Product obj WHERE UPPER(obj.name) LIKE UPPER" +
            "('%'||:name ||'%')")
    Page<ProductMinDTO> findProducts(@Param("name") String name, Pageable pageable);

}
