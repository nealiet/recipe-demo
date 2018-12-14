package com.njt.recipedemo.services;

import com.njt.recipedemo.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();
}
