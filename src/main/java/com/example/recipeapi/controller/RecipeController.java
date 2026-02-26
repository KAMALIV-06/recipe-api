package com.example.recipeapi.controller;

import com.example.recipeapi.dto.RecipeRequestDTO;
import com.example.recipeapi.dto.RecipeResponseDTO;
import com.example.recipeapi.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeResponseDTO> createRecipe(
            @Valid @RequestBody RecipeRequestDTO request) {

        RecipeResponseDTO response =
                recipeService.createRecipe(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/top")
    public ResponseEntity<Map<String, Object>> getTopRecipes(
            @RequestParam(defaultValue = "5") int limit) {

        List<RecipeResponseDTO> recipes =
                recipeService.getTopRecipes(limit);

        return ResponseEntity.ok(
                Map.of("data", recipes)
        );
    }
}