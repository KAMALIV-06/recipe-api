package com.example.recipeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Cuisine is required")
    private String cuisine;

    private Float rating;

    @NotNull(message = "Prep time is required")
    @JsonProperty("prep_time")
    private Integer prepTime;

    @NotNull(message = "Cook time is required")
    @JsonProperty("cook_time")
    private Integer cookTime;

    private String description;

    private NutrientsDTO nutrients;

    private String serves;
}