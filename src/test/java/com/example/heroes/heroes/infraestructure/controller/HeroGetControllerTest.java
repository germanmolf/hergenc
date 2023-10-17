package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

import static com.example.heroes.shared.domain.CriteriaMother.filterString;
import static com.example.heroes.shared.domain.CriteriaMother.orderString;

final class HeroGetControllerTest extends ControllerTestModule {

    @Test
    void find_an_existing_hero() throws Exception {
        String id = "cc77f9b4-3209-11ee-be56-0242ac120002";
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"superheroe\",\"power\":\"superpoder\"}";
        givenThereIsAHero(body);

        assertResponse(String.format("/heroes/%s", id), 200, body);
    }

    @Test
    void not_find_a_non_existing_hero() throws Exception {
        String id = "cc77f9b4-3209-11ee-be56-0242ac120002";
        String body = "{\"code\":\"hero_not_found\",\"message\":\"Not found hero <cc77f9b4-3209-11ee-be56-0242ac120002>\"}";

        assertResponse(String.format("/heroes/%s", id), 404, body);
    }

    @Test
    void search_all_heroes() throws Exception {
        String spiderman = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}";
        String superman = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}";
        String body = "[" + spiderman + "," + superman + "]";
        givenThereIsAHero(spiderman);
        givenThereIsAHero(superman);

        assertResponse("/heroes", 200, body);
    }

    @Test
    void search_by_name() throws Exception {
        String spiderman = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}";
        String superman = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}";
        String params = String.format(filterString, "name", "=", "spiderman");
        String body = "[" + spiderman + "]";
        givenThereIsAHero(spiderman);
        givenThereIsAHero(superman);

        assertResponse(String.format("/heroes?%s", params), 200, body);
    }

    @Test
    void search_by_power() throws Exception {
        String spiderman = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}";
        String superman = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}";
        String params = String.format(filterString, "power", "=", "araña");
        String body = "[" + spiderman + "]";
        givenThereIsAHero(spiderman);
        givenThereIsAHero(superman);

        assertResponse(String.format("/heroes?%s", params), 200, body);
    }

    @Test
    void ignore_not_allowed_filter() throws Exception {
        String spiderman = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}";
        String superman = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}";
        String params = String.format(filterString, "id", "=", "cc77f9b4-3209-11ee-be56-0242ac120002");

        String body = "[" + spiderman + "," + superman + "]";
        givenThereIsAHero(spiderman);
        givenThereIsAHero(superman);

        assertResponse(String.format("/heroes?%s", params), 200, body);
    }

    @Test
    void order_by_name() throws Exception {
        String heroA = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"a\",\"power\":\"araña\"}";
        String heroB = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"b\",\"power\":\"superfuerte\"}";
        String params = String.format(orderString, "name", "asc");
        String body = "[" + heroA + "," + heroB + "]";
        givenThereIsAHero(heroA);
        givenThereIsAHero(heroB);

        assertResponse(String.format("/heroes?%s", params), 200, body);
    }

    @Test
    void order_by_power() throws Exception {
        String heroA = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"a\"}";
        String heroB = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"b\"}";
        String params = String.format(orderString, "power", "asc");
        String body = "[" + heroA + "," + heroB + "]";
        givenThereIsAHero(heroA);
        givenThereIsAHero(heroB);

        assertResponse(String.format("/heroes?%s", params), 200, body);
    }

    @Test
    void ignore_not_allowed_orderBy() throws Exception {
        String spiderman = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}";
        String superman = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}";
        String params = String.format(orderString, "id", "desc");
        String body = "[" + spiderman + "," + superman + "]";
        givenThereIsAHero(spiderman);
        givenThereIsAHero(superman);

        assertResponse(String.format("/heroes?%s", params), 200, body);
    }

    private void givenThereIsAHero(String body) throws Exception {
        assertRequestWithBody("POST", "/heroes", body, 201);
    }
}
