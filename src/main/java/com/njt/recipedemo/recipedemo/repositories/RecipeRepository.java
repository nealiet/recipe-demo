package com.njt.recipedemo.recipedemo.repositories;

import com.njt.recipedemo.recipedemo.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
