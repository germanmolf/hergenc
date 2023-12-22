package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class HeroGetControllerTest extends ControllerTestModule {

    @Test
    void find_an_existing_hero() throws Exception {
        givenThereIsAHero("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"superheroe\",\"power\":\"superpoder\"}");
        String bodyResponse = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"superheroe\"," +
                "\"power\":\"superpoder\"," +
                "\"villainsDefeatedTotal\":0," +
                "\"villainsDefeated\":[]," +
                "\"status\":\"active\"}";

        assertResponse("/heroes/cc77f9b4-3209-11ee-be56-0242ac120002", 200, bodyResponse);
    }

    @Test
    void not_find_a_non_existing_hero() throws Exception {
        String bodyResponse = "{\"code\":\"hero_not_found\",\"message\":\"Not found hero <cc77f9b4-3209-11ee-be56-0242ac120002>\"}";

        assertResponse("/heroes/cc77f9b4-3209-11ee-be56-0242ac120002", 404, bodyResponse);
    }

    @Test
    void search_all_heroes() throws Exception {
        givenThereIsAHero("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}");
        givenThereIsAHero("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}");
        String bodyResponse = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"spiderman\"," +
                "\"power\":\"araña\"," +
                "\"villainsDefeatedTotal\":0," +
                "\"villainsDefeated\":[]," +
                "\"status\":\"active\"}" +
                "," +
                "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"name\":\"superman\"," +
                "\"power\":\"superfuerte\"," +
                "\"villainsDefeatedTotal\":0," +
                "\"villainsDefeated\":[]," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse("/heroes", 200, bodyResponse);
    }

    private void givenThereIsAHero(String body) throws Exception {
        assertRequestWithBody("POST", "/heroes", body, 201);
    }
}
