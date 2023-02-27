package com.foodRecipe.recipeBook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;
    String label;
    @Column(length = 2048)
    String imageUrl;
    int servings;
    int totalTime;
    double calories;
    double caloriesPerServings;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredients_id")
    List<Ingredients> ingredients;
    String source;
    String url;

}
