package com.example.heroes.versus.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

import static com.example.heroes.shared.domain.CriteriaMother.filterString;
import static com.example.heroes.shared.domain.CriteriaMother.orderString;

final class VersusGetControllerTest extends ControllerTestModule {

    @Test
    void find_an_existing_versus() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String id = "cc77f9b4-3209-11ee-be56-0242ac120002";
        String body = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(body);

        assertResponse(String.format("/versus/%s", id), 200, body);
    }

    @Test
    void not_find_a_non_existing_versus() throws Exception {
        String id = "cc77f9b4-3209-11ee-be56-0242ac120002";
        String body = "{\"code\":\"versus_not_found\",\"message\":\"Not found versus <cc77f9b4-3209-11ee-be56-0242ac120002>\"}";

        assertResponse(String.format("/versus/%s", id), 404, body);
    }

    @Test
    void search_all_versus() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String firstVersus = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"none\"}";
        String secondVersus = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"none\"}";
        String body = "[" + firstVersus + "," + secondVersus + "]";
        givenThereIsAVersus(firstVersus);
        givenThereIsAVersus(secondVersus);

        assertResponse("/versus", 200, body);
    }

    @Test
    void search_by_heroId() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAHero("dfe2cf3c-89c2-499c-86b3-b02d7905760f");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        givenThereIsAVillain("fb832bb4-53ea-4917-be96-dc89823b60a9");
        String firstVersus = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"none\"}";
        String secondVersus = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"dfe2cf3c-89c2-499c-86b3-b02d7905760f\"," +
                "\"villainId\":\"fb832bb4-53ea-4917-be96-dc89823b60a9\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(firstVersus);
        givenThereIsAVersus(secondVersus);
        String params = String.format(filterString, "heroId", "=", "daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        String body = "[" + firstVersus + "]";

        assertResponse(String.format("/versus?%s", params), 200, body);
    }

    @Test
    void search_by_villainId() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAHero("dfe2cf3c-89c2-499c-86b3-b02d7905760f");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        givenThereIsAVillain("fb832bb4-53ea-4917-be96-dc89823b60a9");
        String firstVersus = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"none\"}";
        String secondVersus = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"dfe2cf3c-89c2-499c-86b3-b02d7905760f\"," +
                "\"villainId\":\"fb832bb4-53ea-4917-be96-dc89823b60a9\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(firstVersus);
        givenThereIsAVersus(secondVersus);
        String params = String.format(filterString, "villainId", "=", "fb832bb4-53ea-4917-be96-dc89823b60a9");
        String body = "[" + secondVersus + "]";

        assertResponse(String.format("/versus?%s", params), 200, body);
    }

    @Test
    void search_by_defeated() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String firstVersus = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"none\"}";
        String secondVersus = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(firstVersus);
        givenThereIsAVersus(secondVersus);
        String params = String.format(filterString, "defeated", "=", "none");
        String body = "[" + firstVersus + "]";

        assertResponse(String.format("/versus?%s", params), 200, body);
    }

    @Test
    void ignore_not_allowed_filter() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String firstVersus = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"none\"}";
        String secondVersus = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(firstVersus);
        givenThereIsAVersus(secondVersus);
        String params = String.format(filterString, "id", "=", "cc77f9b4-3209-11ee-be56-0242ac120002");
        String body = "[" + firstVersus + "," + secondVersus + "]";

        assertResponse(String.format("/versus?%s", params), 200, body);
    }

    @Test
    void order_by_heroId() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAHero("dfe2cf3c-89c2-499c-86b3-b02d7905760f");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        givenThereIsAVillain("fb832bb4-53ea-4917-be96-dc89823b60a9");
        String firstVersus = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"none\"}";
        String secondVersus = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"dfe2cf3c-89c2-499c-86b3-b02d7905760f\"," +
                "\"villainId\":\"fb832bb4-53ea-4917-be96-dc89823b60a9\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(firstVersus);
        givenThereIsAVersus(secondVersus);
        String params = String.format(orderString, "heroId", "asc");
        String body = "[" + firstVersus + "," + secondVersus + "]";

        assertResponse(String.format("/versus?%s", params), 200, body);
    }

    @Test
    void order_by_villainId() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAHero("dfe2cf3c-89c2-499c-86b3-b02d7905760f");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        givenThereIsAVillain("fb832bb4-53ea-4917-be96-dc89823b60a9");
        String firstVersus = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"none\"}";
        String secondVersus = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"dfe2cf3c-89c2-499c-86b3-b02d7905760f\"," +
                "\"villainId\":\"fb832bb4-53ea-4917-be96-dc89823b60a9\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(firstVersus);
        givenThereIsAVersus(secondVersus);
        String params = String.format(orderString, "villainId", "asc");
        String body = "[" + firstVersus + "," + secondVersus + "]";

        assertResponse(String.format("/versus?%s", params), 200, body);
    }

    @Test
    void ignore_not_allowed_orderBy() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String firstVersus = "{\"id\":\"cc77f9b4-3209-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"none\"}";
        String secondVersus = "{\"id\":\"f555eb50-4827-11ee-be56-0242ac120002\"," +
                "\"heroId\":\"daa93fd0-52cb-46b7-a3d8-624ac2a396e1\"," +
                "\"villainId\":\"df284273-9075-478a-b00c-30db722fa80f\"," +
                "\"defeated\":\"both\"}";
        givenThereIsAVersus(firstVersus);
        givenThereIsAVersus(secondVersus);
        String params = String.format(orderString, "id", "desc");
        String body = "[" + firstVersus + "," + secondVersus + "]";

        assertResponse(String.format("/versus?%s", params), 200, body);
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
