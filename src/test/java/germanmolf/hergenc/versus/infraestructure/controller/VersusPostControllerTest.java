package germanmolf.hergenc.versus.infraestructure.controller;

import germanmolf.hergenc.shared.infraestructure.controller.AcceptanceTestModule;
import org.junit.jupiter.api.Test;

final class VersusPostControllerTest extends AcceptanceTestModule {

    private static final int MILLIS_TO_WAIT = 3000;

    @Test
    void create_a_valid_versus() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String body = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "heroId":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1",
                    "villainId":"df284273-9075-478a-b00c-30db722fa80f",
                    "defeated":"both"
                }""";

        assertRequest("POST", "/versus", body, 201);
    }

    @Test
    void update_hero_on_versus_created() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String versusBody = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "heroId":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1",
                    "villainId":"df284273-9075-478a-b00c-30db722fa80f",
                    "defeated":"both"
                }""";
        givenThereIsAVersus(versusBody);
        String bodyResponse = """
                {
                    "id":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1",
                    "name":"superheroe",
                    "power":"superpoder",
                    "villainsDefeatedTotal":1,
                    "villainsDefeated":["df284273-9075-478a-b00c-30db722fa80f"],
                    "status":"defeated",
                    "villainDefeater":"df284273-9075-478a-b00c-30db722fa80f"
                }""";
        Thread.sleep(MILLIS_TO_WAIT);

        assertResponse("/heroes/daa93fd0-52cb-46b7-a3d8-624ac2a396e1", 200, bodyResponse);
    }

    @Test
    void update_villain_on_versus_created() throws Exception {
        givenThereIsAHero("daa93fd0-52cb-46b7-a3d8-624ac2a396e1");
        givenThereIsAVillain("df284273-9075-478a-b00c-30db722fa80f");
        String versusBody = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "heroId":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1",
                    "villainId":"df284273-9075-478a-b00c-30db722fa80f",
                    "defeated":"both"
                }""";
        givenThereIsAVersus(versusBody);
        String bodyResponse = """
                {
                    "id":"df284273-9075-478a-b00c-30db722fa80f",
                    "name":"supervillano",
                    "power":"superpoder",
                    "heroesDefeatedTotal":1,
                    "heroesDefeated":["daa93fd0-52cb-46b7-a3d8-624ac2a396e1"],
                    "status":"defeated",
                    "heroDefeater":"daa93fd0-52cb-46b7-a3d8-624ac2a396e1"
                }""";
        Thread.sleep(MILLIS_TO_WAIT);

        assertResponse("/villains/df284273-9075-478a-b00c-30db722fa80f", 200, bodyResponse);
    }

    private void givenThereIsAVersus(String body) throws Exception {
        assertRequest("POST", "/versus", body, 201);
    }

    private void givenThereIsAHero(String id) throws Exception {
        String body = """
                {
                    "id":"%s",
                    "name":"superheroe",
                    "power":"superpoder"
                }"""
                .formatted(id);
        assertRequest("POST", "/heroes", body, 201);
    }

    private void givenThereIsAVillain(String id) throws Exception {
        String body = """
                {
                    "id":"%s",
                    "name":"supervillano",
                    "power":"superpoder"
                }"""
                .formatted(id);
        assertRequest("POST", "/villains", body, 201);
    }
}
