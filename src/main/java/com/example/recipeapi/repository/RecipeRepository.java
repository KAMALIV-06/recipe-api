package com.example.recipeapi.repository;

import com.example.recipeapi.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Pageable;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findAllByOrderByRatingDesc(Pageable pageable);
}
