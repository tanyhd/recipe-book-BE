package com.foodRecipe.recipeBook.service;


import com.foodRecipe.recipeBook.external.edamam.EdamamApi;
import com.foodRecipe.recipeBook.model.Ingredients;
import com.foodRecipe.recipeBook.model.Recipe;
import com.foodRecipe.recipeBook.model.RecipeResponseOverview;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.foodRecipe.recipeBook.util.Constant.EDAMAM_APP_ID;
import static com.foodRecipe.recipeBook.util.Constant.EDAMAM_APP_KEY;


@Service
public class RecipeService {

    private final EdamamApi edamamApi;

    public RecipeService(
            EdamamApi edamamApi
    ) {
        this.edamamApi = edamamApi;
    }

    public CompletableFuture<List<Recipe>> getRecipe(String food) {
        return CompletableFuture.supplyAsync(() -> edamamApi.getRecipeOverview(food, EDAMAM_APP_ID, EDAMAM_APP_KEY)).thenApply(recipeResponseOverview -> {
            List<Recipe> recipeList = new ArrayList<>();
            recipeResponseOverview.getHits().forEach(overview -> {
                recipeList.add(Recipe.builder()
                                .label(overview.getRecipe().getLabel())
                                .imageUrl(overview.getRecipe().getImage())
                                .servings(overview.getRecipe().getYield())
                                .totalTime(overview.getRecipe().getTotalTime())
                                .calories(overview.getRecipe().getCalories())
                                .caloriesPerServings(getCaloriesPerServings(overview))
                                .ingredients(getIngredients(overview))
                                .source(overview.getRecipe().getSource())
                                .url(overview.getRecipe().getUrl())
                        .build());
            });
            return recipeList;
        });
    }

    public CompletableFuture<List<Recipe>> getRecipesBaseOnRequirement(String mealRequirement) {
        String requirment[] = mealRequirement.split(" ");
        String diet = requirment[0];
        String mealType = requirment[1];
        String calories = requirment[2];

        return CompletableFuture.supplyAsync(() -> edamamApi.getMealRequirementRecipeOverview(EDAMAM_APP_ID, EDAMAM_APP_KEY, diet, mealType, calories)).thenApply(recipeResponseOverview -> {
            List<Recipe> recipeList = new ArrayList<>();
            recipeResponseOverview.getHits().forEach(overview -> {
                recipeList.add(Recipe.builder()
                        .label(overview.getRecipe().getLabel())
                        .imageUrl(overview.getRecipe().getImage())
                        .servings(overview.getRecipe().getYield())
                        .totalTime(overview.getRecipe().getTotalTime())
                        .calories(overview.getRecipe().getCalories())
                        .caloriesPerServings(getCaloriesPerServings(overview))
                        .ingredients(getIngredients(overview))
                        .source(overview.getRecipe().getSource())
                        .url(overview.getRecipe().getUrl())
                        .build());
            });
            return recipeList;
        });
    }

    private static List<Ingredients> getIngredients(RecipeResponseOverview.Overview overview) {
        List<Ingredients> ingredientsList = new ArrayList<>();
        overview.getRecipe().getIngredients().forEach(ingredients -> {
            ingredientsList.add(Ingredients.builder()
                            .text(ingredients.getText())
                            .quantity(Float.parseFloat(ingredients.getQuantity()))
                            .measure(ingredients.getMeasure())
                            .foodId(ingredients.getFoodId())
                            .imageUrl(ingredients.getImage())
                            .food(ingredients.getFood())
                    .build());
        });
        return ingredientsList;
    }

    public static double getCaloriesPerServings(RecipeResponseOverview.Overview overview) {
        int servingsPerPax = overview.getRecipe().getYield();
        double totalCalories = overview.getRecipe().getCalories();
        return totalCalories/servingsPerPax;
    }
}
