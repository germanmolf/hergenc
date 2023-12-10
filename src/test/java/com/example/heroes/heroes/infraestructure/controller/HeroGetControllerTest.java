package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

import static com.example.heroes.shared.domain.CriteriaMother.filterString;
import static com.example.heroes.shared.domain.CriteriaMother.orderString;

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

    @Test
    void search_by_name() throws Exception {
        givenThereIsAHero("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}");
        givenThereIsAHero("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}");
        String params = String.format(filterString, "name", "=", "spiderman");
        String bodyResponse = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"spiderman\"," +
                "\"power\":\"araña\"," +
                "\"villainsDefeatedTotal\":0," +
                "\"villainsDefeated\":[]," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse(String.format("/heroes?%s", params), 200, bodyResponse);
    }

    @Test
    void search_by_power() throws Exception {
        givenThereIsAHero("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}");
        givenThereIsAHero("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}");
        String params = String.format(filterString, "power", "=", "araña");
        String bodyResponse = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"spiderman\"," +
                "\"power\":\"araña\"," +
                "\"villainsDefeatedTotal\":0," +
                "\"villainsDefeated\":[]," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse(String.format("/heroes?%s", params), 200, bodyResponse);
    }

    @Test
    void ignore_not_allowed_filter() throws Exception {
        givenThereIsAHero("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}");
        givenThereIsAHero("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}");
        String params = String.format(filterString, "id", "=", "cc77f9b4-3209-11ee-be56-0242ac120002");
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

        assertResponse(String.format("/heroes?%s", params), 200, bodyResponse);
    }

    @Test
    void order_by_name() throws Exception {
        givenThereIsAHero("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"a\",\"power\":\"araña\"}");
        givenThereIsAHero("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"b\",\"power\":\"superfuerte\"}");
        String params = String.format(orderString, "name", "asc");
        String bodyResponse = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"a\"," +
                "\"power\":\"araña\"," +
                "\"villainsDefeatedTotal\":0," +
                "\"villainsDefeated\":[]," +
                "\"status\":\"active\"}" +
                "," +
                "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"name\":\"b\"," +
                "\"power\":\"superfuerte\"," +
                "\"villainsDefeatedTotal\":0," +
                "\"villainsDefeated\":[]," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse(String.format("/heroes?%s", params), 200, bodyResponse);
    }

    @Test
    void order_by_power() throws Exception {
        givenThereIsAHero("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"a\"}");
        givenThereIsAHero("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"b\"}");
        String params = String.format(orderString, "power", "asc");
        String bodyResponse = "[" +
                "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"name\":\"spiderman\"," +
                "\"power\":\"a\"," +
                "\"villainsDefeatedTotal\":0," +
                "\"villainsDefeated\":[]," +
                "\"status\":\"active\"}" +
                "," +
                "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"name\":\"superman\"," +
                "\"power\":\"b\"," +
                "\"villainsDefeatedTotal\":0," +
                "\"villainsDefeated\":[]," +
                "\"status\":\"active\"}" +
                "]";

        assertResponse(String.format("/heroes?%s", params), 200, bodyResponse);
    }

    @Test
    void ignore_not_allowed_orderBy() throws Exception {
        givenThereIsAHero("{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\",\"name\":\"spiderman\",\"power\":\"araña\"}");
        givenThereIsAHero("{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\",\"name\":\"superman\",\"power\":\"superfuerte\"}");
        String params = String.format(orderString, "id", "desc");
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

        assertResponse(String.format("/heroes?%s", params), 200, bodyResponse);
    }

    private void givenThereIsAHero(String body) throws Exception {
        assertRequestWithBody("POST", "/heroes", body, 201);
    }
}
