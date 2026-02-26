package com.example.recipeapi.util;

import com.example.recipeapi.dto.RecipeRequestDTO;
import com.example.recipeapi.entity.Recipe;
import com.example.recipeapi.repository.RecipeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JsonDataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (recipeRepository.count() > 0) {
            System.out.println("Data already loaded. Skipping...");
            return;
        }

        InputStream inputStream =
                new ClassPathResource("recipes.json").getInputStream();

        Map<String, RecipeRequestDTO> recipeMap =
                objectMapper.readValue(inputStream,
                        new TypeReference<Map<String, RecipeRequestDTO>>() {});

        List<RecipeRequestDTO> recipeDTOList =
                new ArrayList<>(recipeMap.values());

        List<Recipe> recipeEntities = new ArrayList<>();

        for (RecipeRequestDTO dto : recipeDTOList) {

            if (dto.getTitle() == null) continue;

            Recipe recipe = Recipe.builder()
                    .title(dto.getTitle())
                    .cuisine(dto.getCuisine())
                    .rating(dto.getRating())
                    .prepTime(dto.getPrepTime())
                    .cookTime(dto.getCookTime())
                    .totalTime(
                            dto.getPrepTime() != null && dto.getCookTime() != null
                                    ? dto.getPrepTime() + dto.getCookTime()
                                    : null
                    )
                    .description(dto.getDescription())
                    .nutrients(objectMapper.writeValueAsString(dto.getNutrients()))
                    .serves(dto.getServes())
                    .build();

            recipeEntities.add(recipe);
        }

        recipeRepository.saveAll(recipeEntities);

        System.out.println("✅ JSON Loaded Successfully. Records inserted: "
                + recipeEntities.size());
    }
}