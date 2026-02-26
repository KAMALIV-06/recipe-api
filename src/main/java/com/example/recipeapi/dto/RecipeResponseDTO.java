package com.example.recipeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeResponseDTO {

    private Integer id;
    private String title;
    private String cuisine;
    private Float rating;

    @JsonProperty("prep_time")
    private Integer prepTime;

    @JsonProperty("cook_time")
    private Integer cookTime;

    @JsonProperty("total_time")
    private Integer totalTime;

    private String description;
    private Object nutrients;
    private String serves;
}