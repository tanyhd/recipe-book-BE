package com.foodRecipe.recipeBook.controller;

import com.foodRecipe.recipeBook.model.JwtAuthentication.AuthenticationRequest;
import com.foodRecipe.recipeBook.model.JwtAuthentication.AuthenticationResponse;
import com.foodRecipe.recipeBook.model.JwtAuthentication.RegisterRequest;
import com.foodRecipe.recipeBook.repository.UserRepository;
import com.foodRecipe.recipeBook.service.JwtAuthentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    /* For Testing
    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User updatedUser) {
        User existingUser = userRepository.findByEmail(updatedUser.getEmail()).get();
        existingUser.setLineItem(updatedUser.getLineItem());
        existingUser.setRecipesList(updatedUser.getRecipesList());
        var user = userRepository.save(existingUser);
        System.out.println(user);
        return ResponseEntity.ok("saved");
    }
     */
}
