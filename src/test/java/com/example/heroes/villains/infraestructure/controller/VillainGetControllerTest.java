package com.example.heroes.villains.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class VillainGetControllerTest extends ControllerTestModule {

    @Test
    void find_an_existing_villain() throws Exception {
        String id = "cc77f9b4-3209-11ee-be56-0242ac120002";
        String body = "{\"id\":\"" + id + "\",\"name\":\"Joker\",\"power\":\"Mafioso\"}";
        givenThereIsAVillain(body);

        assertResponse(String.format("/villains/%s", id), 200, body);
    }

    private void givenThereIsAVillain(String body) throws Exception {
        assertRequestWithBody("POST", "/villains", body, 201);
    }
}
