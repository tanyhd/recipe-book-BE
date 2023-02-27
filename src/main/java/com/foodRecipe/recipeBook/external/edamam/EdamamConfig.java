package com.foodRecipe.recipeBook.external.edamam;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EdamamConfig {

    @Value("${edamam.host}")
    String edamamHost;

    @Bean
    public EdamamApi edamamApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(new ObjectMapper()))
                .decoder(new JacksonDecoder(new ObjectMapper()))
                .target(EdamamApi.class, edamamHost);
    }

}
