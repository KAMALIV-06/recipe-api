package com.example.recipeapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class    Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String cuisine;

    private Float rating;

    @Column(name = "prep_time", nullable = false)
    private Integer prepTime;

    @Column(name = "cook_time", nullable = false)
    private Integer cookTime;

    @Column(name = "total_time")
    private Integer totalTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "JSON")
    private String nutrients;

    private String serves;
}
