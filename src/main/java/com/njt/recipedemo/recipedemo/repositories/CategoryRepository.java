package com.njt.recipedemo.recipedemo.repositories;

import com.njt.recipedemo.recipedemo.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
