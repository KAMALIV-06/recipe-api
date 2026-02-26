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

    private String title;

    private String cuisine;

    private Float rating;


    @JsonProperty("prep_time")
    private Integer prepTime;


    @JsonProperty("cook_time")
    private Integer cookTime;

    private String description;

    private NutrientsDTO nutrients;

    private String serves;
}


