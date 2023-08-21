package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class HeroPutControllerTest extends ControllerTestModule {

    @Test
    void create_a_valid_hero() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"superheroe\",\"power\":\"superpoder\"}";

        assertRequestWithBody("POST", "/heroes", body, 201);
    }

    @Test
    void not_create_hero_when_already_exists() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"superheroe\",\"power\":\"superpoder\"}";
        assertRequestWithBody("POST", "/heroes", body, 201);

        assertRequestWithBody("POST", "/heroes", body, 409);
    }

    @Test
    void not_create_hero_when_name_is_null() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"power\":\"superpoder\"}";

        assertRequestWithBody("POST", "/heroes", body, 400);
    }

    @Test
    void not_create_hero_when_name_has_invalid_length() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"superheroesuperheroesuper\",\"power\":\"superpoder\"}";

        assertRequestWithBody("POST", "/heroes", body, 400);
    }

    @Test
    void not_create_hero_when_power_is_null() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"superheroe\"}";

        assertRequestWithBody("POST", "/heroes", body, 400);
    }

    @Test
    void not_create_hero_when_power_has_invalid_length() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"superheroe\",\"power\":\"superpodersuperpodersuperpodersuper\"}";

        assertRequestWithBody("POST", "/heroes", body, 400);
    }
}
