package com.caue.democommerce.repositories;


import com.caue.democommerce.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
