package com.foodRecipe.recipeBook.service.JwtAuthentication;

import com.foodRecipe.recipeBook.model.JwtAuthentication.AuthenticationRequest;
import com.foodRecipe.recipeBook.model.JwtAuthentication.AuthenticationResponse;
import com.foodRecipe.recipeBook.model.JwtAuthentication.RegisterRequest;
import com.foodRecipe.recipeBook.model.user.Role;
import com.foodRecipe.recipeBook.model.user.User;
import com.foodRecipe.recipeBook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail((request.getEmail())).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .recipesList(user.getRecipesList())
                .lineItem(user.getLineItem())
                .token(jwtToken)
                .build();
    }
}
