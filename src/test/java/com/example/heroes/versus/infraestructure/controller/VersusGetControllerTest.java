package com.example.heroes.versus.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;

final class VersusGetControllerTest extends ControllerTestModule {

    @Test
    void find_an_existing_versus() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String body = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "heroId":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1",
                    "villainId":"df284273-9075-478a-b00c-30db722fa80f",
                    "defeated":"both"
                }""";
        givenThereIsAVersus(body);

        assertResponse("/versus/cc77f9b4-3209-11ee-be56-0242ac120002", 200, body);
    }
    
    @Test
    void search_all_versus() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String firstVersus = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "heroId":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1",
                    "villainId":"df284273-9075-478a-b00c-30db722fa80f",
                    "defeated":"none"
                }""";
        String secondVersus = """
                {
                    "id":"f555eb50-4827-11ee-be56-0242ac120002",
                    "heroId":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1",
                    "villainId":"df284273-9075-478a-b00c-30db722fa80f",
                    "defeated":"none"
                }""";
        String body = """
                [
                    {
                        "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                        "heroId":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1",
                        "villainId":"df284273-9075-478a-b00c-30db722fa80f",
                        "defeated":"none"
                    },
                    {
                        "id":"f555eb50-4827-11ee-be56-0242ac120002",
                        "heroId":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1",
                        "villainId":"df284273-9075-478a-b00c-30db722fa80f",
                        "defeated":"none"
                    }
                ]""";
        givenThereIsAVersus(firstVersus);
        givenThereIsAVersus(secondVersus);

        assertResponse("/versus", 200, body);
    }

    private void givenThereIsAVersus(String body) throws Exception {
        assertRequestWithBody("POST", "/versus", body, 201);
    }

    private void givenThereIsAHero(String id) throws Exception {
        String body = """
                {
                    "id":"%s",
                    "name":"superheroe",
                    "power":"superpoder"
                }"""
                .formatted(id);
        assertRequestWithBody("POST", "/heroes", body, 201);
    }

    private void givenThereIsAVillain(String id) throws Exception {
        String body = """
                {
                    "id":"%s",
                    "name":"supervillano",
                    "power":"superpoder"
                }"""
                .formatted(id);
        assertRequestWithBody("POST", "/villains", body, 201);
    }
}
