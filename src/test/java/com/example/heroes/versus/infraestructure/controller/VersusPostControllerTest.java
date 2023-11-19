package com.example.heroes.versus.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class VersusPostControllerTest extends ControllerTestModule {

    @Test
    void create_a_valid_versus() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"both\"}";

        assertRequestWithBody("POST", "/versus", body, 201);
    }

    @Test
    void not_create_versus_when_defeated_is_null() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"}";

        assertRequestWithBody("POST", "/versus", body, 400);
    }

    @Test
    void not_create_versus_when_defeated_has_invalid_value() throws Exception {
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"qwe\"}";

        assertRequestWithBody("POST", "/versus", body, 400);
    }

    @Test
    void not_create_versus_when_already_exists() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(body);

        assertRequestWithBody("POST", "/versus", body, 409);
    }

    @Test
    void not_create_versus_when_hero_is_already_defeated() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String bodyDefeated = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(bodyDefeated);
        givenThereIsAVillain("cc77f9b4-3209-11ee-be56-0242ac120002");
        String body = "{\"id\":\"7968f96f-d0c4-4a88-8a3c-03dff2f14c10\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"defeated\":\"both\"}";

        assertRequestWithBody("POST", "/versus", body, 409);
    }

    @Test
    void not_create_versus_when_villain_is_already_defeated() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String bodyDefeated = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(bodyDefeated);
        givenThereIsAHero("cc77f9b4-3209-11ee-be56-0242ac120002");
        String body = "{\"id\":\"7968f96f-d0c4-4a88-8a3c-03dff2f14c10\"," +
                "\"heroId\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"both\"}";

        assertRequestWithBody("POST", "/versus", body, 409);
    }

    private void givenThereIsAVersus(String body) throws Exception {
        assertRequestWithBody("POST", "/versus", body, 201);
    }

    private void givenThereIsAHero(String id) throws Exception {
        String body = String.format("{\"id\":\"%s\",\"name\":\"superheroe\",\"power\":\"superpoder\"}", id);
        assertRequestWithBody("POST", "/heroes", body, 201);
    }

    private void givenThereIsAVillain(String id) throws Exception {
        String body = String.format("{\"id\":\"%s\",\"name\":\"supervillano\",\"power\":\"superpoder\"}", id);
        assertRequestWithBody("POST", "/villains", body, 201);
    }
}
