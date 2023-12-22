package com.example.heroes.villains.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class VillainGetControllerTest extends ControllerTestModule {

    @Test
    void find_an_existing_villain() throws Exception {
        givenThereIsAVillain("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"supervillano\",\"power\":\"superpoder\"}");
        String bodyResponse = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"supervillano\"," +
                "\"power\":\"superpoder\"," +
                "\"heroesDefeated\":[]," +
                "\"heroesDefeatedTotal\":0," +
                "\"status\":\"active\"}";

        assertResponse("/villains/cc77f9b4-3209-11ee-be56-0242ac120002", 200, bodyResponse);
    }

    @Test
    void not_find_a_non_existing_villain() throws Exception {
        String bodyResponse = "{\"code\":\"villain_not_found\",\"message\":\"Not found villain <cc77f9b4-3209-11ee-be56-0242ac120002>\"}";

        assertResponse("/villains/cc77f9b4-3209-11ee-be56-0242ac120002", 404, bodyResponse);
    }

    @Test
    void search_all_villains() throws Exception {
        givenThereIsAVillain("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}");
        givenThereIsAVillain("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}");
        String body = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"joker\"," +
                "\"power\":\"está loco\"," +
                "\"heroesDefeated\":[]," +
                "\"heroesDefeatedTotal\":0," +
                "\"status\":\"active\"}" +
                "," +
                "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"name\":\"duende verde\"," +
                "\"power\":\"también loco\"," +
                "\"heroesDefeated\":[]," +
                "\"heroesDefeatedTotal\":0," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse("/villains", 200, body);
    }

    private void givenThereIsAVillain(String body) throws Exception {
        assertRequestWithBody("POST", "/villains", body, 201);
    }
}
