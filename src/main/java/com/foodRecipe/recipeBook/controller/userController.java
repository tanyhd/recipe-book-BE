package com.foodRecipe.recipeBook.controller;

import com.foodRecipe.recipeBook.model.user.User;
import com.foodRecipe.recipeBook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class userController {

    private final UserRepository userRepository;

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User updatedUser) {
        User existingUser = userRepository.findByEmail(updatedUser.getEmail()).get();
        existingUser.setLineItem(updatedUser.getLineItem());
        existingUser.setRecipesList(updatedUser.getRecipesList());
        var user = userRepository.save(existingUser);
        System.out.println(user);
        return ResponseEntity.ok("saved");
    }
}
