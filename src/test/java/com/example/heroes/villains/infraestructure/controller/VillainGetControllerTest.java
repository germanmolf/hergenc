package com.example.heroes.villains.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

import static com.example.heroes.shared.domain.CriteriaMother.filterString;
import static com.example.heroes.shared.domain.CriteriaMother.orderString;

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

    @Test
    void search_by_name() throws Exception {
        givenThereIsAVillain("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}");
        givenThereIsAVillain("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}");
        String params = String.format(filterString, "name", "=", "joker");

        String body = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"joker\"," +
                "\"power\":\"está loco\"," +
                "\"heroesDefeated\":[]," +
                "\"heroesDefeatedTotal\":0," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void search_by_power() throws Exception {
        givenThereIsAVillain("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}");
        givenThereIsAVillain("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}");
        String params = String.format(filterString, "power", "=", "está loco");

        String body = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"joker\"," +
                "\"power\":\"está loco\"," +
                "\"heroesDefeated\":[]," +
                "\"heroesDefeatedTotal\":0," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void ignore_not_allowed_filter() throws Exception {
        givenThereIsAVillain("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}");
        givenThereIsAVillain("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}");
        String params = String.format(filterString, "id", "=", "cc77f9b4-3209-11ee-be56-0242ac120002");

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

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void order_by_name() throws Exception {
        givenThereIsAVillain("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"a\",\"power\":\"está loco\"}");
        givenThereIsAVillain("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"b\",\"power\":\"también loco\"}");
        String params = String.format(orderString, "name", "asc");
        String body = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"a\"," +
                "\"power\":\"está loco\"," +
                "\"heroesDefeated\":[]," +
                "\"heroesDefeatedTotal\":0," +
                "\"status\":\"active\"}" +
                "," +
                "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"name\":\"b\"," +
                "\"power\":\"también loco\"," +
                "\"heroesDefeated\":[]," +
                "\"heroesDefeatedTotal\":0," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void order_by_power() throws Exception {
        givenThereIsAVillain("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"a\"}");
        givenThereIsAVillain("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"b\"}");
        String params = String.format(orderString, "power", "asc");
        String body = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"joker\"," +
                "\"power\":\"a\"," +
                "\"heroesDefeated\":[]," +
                "\"heroesDefeatedTotal\":0," +
                "\"status\":\"active\"}" +
                "," +
                "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"name\":\"duende verde\"," +
                "\"power\":\"b\"," +
                "\"heroesDefeated\":[]," +
                "\"heroesDefeatedTotal\":0," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void ignore_not_allowed_orderBy() throws Exception {
        givenThereIsAVillain("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}");
        givenThereIsAVillain("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}");
        String params = String.format(orderString, "id", "desc");

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

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    private void givenThereIsAVillain(String body) throws Exception {
        assertRequestWithBody("POST", "/villains", body, 201);
    }
}
