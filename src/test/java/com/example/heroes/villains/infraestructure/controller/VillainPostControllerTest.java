package com.example.heroes.villains.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class VillainPostControllerTest extends ControllerTestModule {

    @Test
    void create_a_valid_villain() throws Exception {
        String body = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"Joker",
                    "power":"está loco"
                }""";

        assertRequestWithBody("POST", "/villains", body, 201);
    }

}
