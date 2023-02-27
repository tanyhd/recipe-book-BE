package com.foodRecipe.recipeBook.external.edamam;


import com.foodRecipe.recipeBook.model.RecipeResponseOverview;
import feign.Param;
import feign.RequestLine;


public interface EdamamApi {

    @RequestLine("GET /api/recipes/v2?type=public&q={food}&app_id={appId}&app_key={appKey}")
    RecipeResponseOverview getRecipeOverview(
            @Param("food") String food,
            @Param("appId") String appId,
            @Param("appKey") String appKey
    );

    @RequestLine("GET /api/recipes/v2?type=public&app_id={appId}&app_key={appKey}&diet={diet}&mealType={mealType}&calories={calories}&imageSize=REGULAR&random=true")
    RecipeResponseOverview getMealRequirementRecipeOverview(
            @Param("appId") String appId,
            @Param("appKey") String appKey,
            @Param("diet") String diet,
            @Param("mealType") String mealType,
            @Param("calories") String calories
    );
}
