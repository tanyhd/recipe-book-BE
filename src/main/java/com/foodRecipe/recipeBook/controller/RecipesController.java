package com.foodRecipe.recipeBook.controller;

import com.foodRecipe.recipeBook.model.Recipe;
import com.foodRecipe.recipeBook.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class RecipesController {
    private final RecipeService recipeService;

    @GetMapping(path="/api/recipes/{food}")
    public CompletableFuture<ResponseEntity<List<Recipe>>> getRecipes(@PathVariable String food) {
        return recipeService.getRecipe(food).thenApply(ResponseEntity::ok);
    }

    @GetMapping(path="/api/recipes/meal/{mealRequirement}")
    public CompletableFuture<ResponseEntity<List<Recipe>>> getRecipesBaseOnRequirement(@PathVariable String mealRequirement) {
        return recipeService.getRecipesBaseOnRequirement(mealRequirement).thenApply(ResponseEntity::ok);
    }
}
