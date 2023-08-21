package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class HeroGetControllerTest extends ControllerTestModule {

    @Test
    void find_an_existing_hero() throws Exception {
        String id = "cc77f9b4-3209-11ee-be56-0242ac120002";
        String body = "{\"id\":\"" + id + "\",\"name\":\"superheroe\",\"power\":\"superpoder\"}";
        givenThereIsAHero(id, body);

        assertResponse(String.format("/heroes/%s", id), 200, body);
    }

    private void givenThereIsAHero(String id, String body) throws Exception {
        assertRequestWithBody("POST", "/heroes", body, 201);
    }
}
