package com.example.recipeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NutrientsDTO {

    private String calories;
    private String carbohydrateContent;
    private String proteinContent;
    private String fatContent;
}
