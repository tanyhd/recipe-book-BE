package com.foodRecipe.recipeBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Ingredients {
    @Id
    @GeneratedValue
    private Long id;
    String text;
    float quantity;
    String measure;
    String foodId;
    String imageUrl;
    String food;
}
