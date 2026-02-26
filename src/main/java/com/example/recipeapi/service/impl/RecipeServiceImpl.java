package com.example.recipeapi.service.impl;

import com.example.recipeapi.dto.RecipeRequestDTO;
import com.example.recipeapi.dto.RecipeResponseDTO;
import com.example.recipeapi.entity.Recipe;
import com.example.recipeapi.repository.RecipeRepository;
import com.example.recipeapi.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;

    @Override
    public RecipeResponseDTO createRecipe(RecipeRequestDTO request) {

        int totalTime = request.getPrepTime() + request.getCookTime();

        Recipe recipe = Recipe.builder()
                .title(request.getTitle())
                .cuisine(request.getCuisine())
                .rating(request.getRating())
                .prepTime(request.getPrepTime())
                .cookTime(request.getCookTime())
                .totalTime(totalTime)
                .description(request.getDescription())
                .nutrients(convertNutrientsToString(request))
                .serves(request.getServes())
                .build();

        Recipe saved = recipeRepository.save(recipe);

        return convertToResponse(saved);
    }
    @Override
    public List<RecipeResponseDTO> getTopRecipes(int limit) {

        if (limit <= 0) {
            limit = 5;
        }

        Pageable pageable = PageRequest.of(0, limit);

        List<Recipe> recipes =
                recipeRepository.findAllByOrderByRatingDesc(pageable);

        return recipes.stream()
                .map(this::convertToResponse)
                .toList();
    }

    private String convertNutrientsToString(RecipeRequestDTO request) {
        try {
            return objectMapper.writeValueAsString(request.getNutrients());
        } catch (Exception e) {
            return null;
        }
    }

    private RecipeResponseDTO convertToResponse(Recipe recipe) {
        try {
            return RecipeResponseDTO.builder()
                    .id(recipe.getId())
                    .title(recipe.getTitle())
                    .cuisine(recipe.getCuisine())
                    .rating(recipe.getRating())
                    .prepTime(recipe.getPrepTime())
                    .cookTime(recipe.getCookTime())
                    .totalTime(recipe.getTotalTime())
                    .description(recipe.getDescription())
                    .nutrients(
                            objectMapper.readValue(
                                    recipe.getNutrients(), Object.class))
                    .serves(recipe.getServes())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error converting response");
        }
    }
}