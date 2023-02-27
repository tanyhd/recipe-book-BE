package com.foodRecipe.recipeBook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;


@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeResponseOverview implements Serializable {
    private List<Overview> hits;


    @Data
    @SuperBuilder(toBuilder = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Overview implements Serializable {
        private Recipe recipe;
    }

    @Data
    @SuperBuilder(toBuilder = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Recipe implements Serializable {
        private String uri;
        private String label;
        private String image;
        private String source;
        private String url;
        private Integer yield;
        private List<String> dietLabels;
        private List<String> healthLabels;
        private List<String> ingredientLines;
        private List<Ingredients> ingredients;
        private Double calories;
        private Double totalWeight;
        private Integer totalTime;
        private List<String> cuisineType;
        private List<String> mealType;
        private List<String> dishType;
    }

    @Data
    @SuperBuilder(toBuilder = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ingredients implements Serializable {
        private String text;
        private String quantity;
        private String measure;
        private String food;
        private String weight;
        private String foodCategory;
        private String foodId;
        private String image;
    }
}
