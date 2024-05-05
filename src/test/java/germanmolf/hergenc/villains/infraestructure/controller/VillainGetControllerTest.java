package germanmolf.hergenc.villains.infraestructure.controller;

import germanmolf.hergenc.shared.infraestructure.controller.AcceptanceTestModule;
import org.junit.jupiter.api.Test;

final class VillainGetControllerTest extends AcceptanceTestModule {

    @Test
    void find_an_existing_villain() throws Exception {
        String villain = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"supervillano",
                    "power":"superpoder"
                }""";
        givenThereIsAVillain(villain);
        String bodyResponse = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"supervillano",
                    "power":"superpoder",
                    "heroesDefeated":[],
                    "heroesDefeatedTotal":0,
                    "status":"active"
                }""";

        assertResponse("/villains/cc77f9b4-3209-11ee-be56-0242ac120002", 200, bodyResponse);
    }

    @Test
    void search_all_villains() throws Exception {
        String aVillain = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"joker",
                    "power":"está loco"
                }""";
        String anotherVillain = """
                {
                    "id":"f555eb50-4827-11ee-be56-0242ac120002",
                    "name":"duende verde",
                    "power":"también loco"
                }""";
        givenThereIsAVillain(aVillain);
        givenThereIsAVillain(anotherVillain);
        String body = """
                [
                    {
                        "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                        "name":"joker",
                        "power":"está loco",
                        "heroesDefeated":[],
                        "heroesDefeatedTotal":0,
                        "status":"active"
                    },
                    {
                        "id":"f555eb50-4827-11ee-be56-0242ac120002",
                        "name":"duende verde",
                        "power":"también loco",
                        "heroesDefeated":[],
                        "heroesDefeatedTotal":0,
                        "status":"active"
                    }
                ]""";

        assertResponse("/villains", 200, body);
    }

    private void givenThereIsAVillain(String body) throws Exception {
        assertRequest("POST", "/villains", body, 201);
    }
}
