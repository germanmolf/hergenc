package com.example.heroes.villains.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

import static com.example.heroes.shared.domain.CriteriaMother.filterString;
import static com.example.heroes.shared.domain.CriteriaMother.orderString;

final class VillainGetControllerTest extends ControllerTestModule {

    @Test
    void find_an_existing_villain() throws Exception {
        String id = "cc77f9b4-3209-11ee-be56-0242ac120002";
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"supervillano\",\"power\":\"superpoder\"}";
        givenThereIsAVillain(body);

        assertResponse(String.format("/villains/%s", id), 200, body);
    }

    @Test
    void not_find_a_non_existing_villain() throws Exception {
        String id = "cc77f9b4-3209-11ee-be56-0242ac120002";
        String body = "{\"code\":\"villain_not_found\",\"message\":\"Not found villain <cc77f9b4-3209-11ee-be56-0242ac120002>\"}";

        assertResponse(String.format("/villains/%s", id), 404, body);
    }

    @Test
    void search_all_villains() throws Exception {
        String joker = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}";
        String duendeVerde = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}";
        String body = "[" + joker + "," + duendeVerde + "]";
        givenThereIsAVillain(joker);
        givenThereIsAVillain(duendeVerde);

        assertResponse("/villains", 200, body);
    }

    @Test
    void search_by_name() throws Exception {
        String joker = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}";
        String duendeVerde = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}";
        String params = String.format(filterString, "name", "=", "joker");

        String body = "[" + joker + "]";
        givenThereIsAVillain(joker);
        givenThereIsAVillain(duendeVerde);

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void search_by_power() throws Exception {
        String joker = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}";
        String duendeVerde = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}";
        String params = String.format(filterString, "power", "=", "está loco");

        String body = "[" + joker + "]";
        givenThereIsAVillain(joker);
        givenThereIsAVillain(duendeVerde);

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void ignore_not_allowed_filter() throws Exception {
        String joker = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}";
        String duendeVerde = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}";
        String params = String.format(filterString, "id", "=", "cc77f9b4-3209-11ee-be56-0242ac120002");

        String body = "[" + joker + "," + duendeVerde + "]";
        givenThereIsAVillain(joker);
        givenThereIsAVillain(duendeVerde);

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void order_by_name() throws Exception {
        String villainA = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"a\",\"power\":\"está loco\"}";
        String villainB = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"b\",\"power\":\"también loco\"}";
        String params = String.format(orderString, "name", "asc");
        String body = "[" + villainA + "," + villainB + "]";
        givenThereIsAVillain(villainA);
        givenThereIsAVillain(villainB);

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void order_by_power() throws Exception {
        String villainA = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"a\"}";
        String villainB = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"b\"}";
        String params = String.format(orderString, "power", "asc");
        String body = "[" + villainA + "," + villainB + "]";
        givenThereIsAVillain(villainA);
        givenThereIsAVillain(villainB);

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    @Test
    void ignore_not_allowed_orderBy() throws Exception {
        String joker = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"joker\",\"power\":\"está loco\"}";
        String duendeVerde = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"duende verde\",\"power\":\"también loco\"}";
        String params = String.format(orderString, "id", "desc");
        String body = "[" + joker + "," + duendeVerde + "]";
        givenThereIsAVillain(joker);
        givenThereIsAVillain(duendeVerde);

        assertResponse(String.format("/villains?%s", params), 200, body);
    }

    private void givenThereIsAVillain(String body) throws Exception {
        assertRequestWithBody("POST", "/villains", body, 201);
    }
}
