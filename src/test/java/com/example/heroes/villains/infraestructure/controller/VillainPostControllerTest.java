package com.example.heroes.villains.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class VillainPostControllerTest extends ControllerTestModule {

    @Test
    void create_a_valid_villain() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"Joker\"}";

        assertRequestWithBody("POST", "/villains", body, 201);
    }

    @Test
    void not_create_villain_when_already_exists() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"Joker\"}";

        assertRequestWithBody("POST", "/villains", body, 201);

        assertRequestWithBody("POST", "/villains", body, 409);
    }
    //not_create_hero_when_already_exists
    //not_create_hero_when_name_is_null
    //not_create_hero_when_name_has_invalid_length
}
