package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class HeroPostControllerTest extends ControllerTestModule {

    @Test
    void create_a_valid_hero() throws Exception {
        String body = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"superheroe",
                    "power":"superpoder"
                }""";

        assertRequestWithBody("POST", "/heroes", body, 201);
    }

}
