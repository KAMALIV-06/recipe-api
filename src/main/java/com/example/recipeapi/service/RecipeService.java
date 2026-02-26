package com.example.recipeapi.service;

import com.example.recipeapi.dto.RecipeRequestDTO;
import com.example.recipeapi.dto.RecipeResponseDTO;

import java.util.List;

public interface RecipeService {

    RecipeResponseDTO createRecipe(RecipeRequestDTO request);
    List<RecipeResponseDTO> getTopRecipes(int limit);
}