package com.njt.recipedemo.services;

import com.njt.recipedemo.domain.Recipe;
import com.njt.recipedemo.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public Set<Recipe> getRecipes() {
        log.debug("I am in the service");
       Set<Recipe> recipeSet = new HashSet<>();
       recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
       return recipeSet;
    }
}
