package germanmolf.hergenc.heroes.infraestructure.controller;

import germanmolf.hergenc.shared.infraestructure.controller.AcceptanceTestModule;
import org.junit.jupiter.api.Test;

final class HeroGetControllerTest extends AcceptanceTestModule {

    @Test
    void find_an_existing_hero() throws Exception {
        String hero = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"superheroe",
                    "power":"superpoder"
                }""";
        givenThereIsAHero(hero);
        String bodyResponse = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"superheroe",
                    "power":"superpoder",
                    "villainsDefeatedTotal":0,
                    "villainsDefeated":[],
                    "status":"active"
                }""";

        assertResponse("/heroes/cc77f9b4-3209-11ee-be56-0242ac120002", 200, bodyResponse);
    }

    @Test
    void search_all_heroes() throws Exception {
        String aHero = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"spiderman",
                    "power":"araña"
                }""";
        String anotherHero = """
                {
                    "id":"f555eb50-4827-11ee-be56-0242ac120002",
                    "name":"superman",
                    "power":"superfuerte"
                }""";
        givenThereIsAHero(aHero);
        givenThereIsAHero(anotherHero);
        String bodyResponse = """
                [
                    {
                        "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                        "name":"spiderman",
                        "power":"araña",
                        "villainsDefeatedTotal":0,
                        "villainsDefeated":[],
                        "status":"active"
                    },
                    {
                        "id":"f555eb50-4827-11ee-be56-0242ac120002",
                        "name":"superman",
                        "power":"superfuerte",
                        "villainsDefeatedTotal":0,
                        "villainsDefeated":[],
                        "status":"active"
                    }
                ]""";

        assertResponse("/heroes", 200, bodyResponse);
    }

    private void givenThereIsAHero(String body) throws Exception {
        assertRequest("POST", "/heroes", body, 201);
    }
}
