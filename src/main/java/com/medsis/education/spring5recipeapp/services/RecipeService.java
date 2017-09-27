package com.medsis.education.spring5recipeapp.services;

import com.medsis.education.spring5recipeapp.domain.Recipe;

import java.util.Set;


public interface RecipeService {

    public Set<Recipe> getRecipes();
    public Recipe findById(Long id);
}
