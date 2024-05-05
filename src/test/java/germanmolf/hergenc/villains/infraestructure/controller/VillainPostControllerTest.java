package germanmolf.hergenc.villains.infraestructure.controller;

import germanmolf.hergenc.shared.infraestructure.controller.AcceptanceTestModule;
import org.junit.jupiter.api.Test;

final class VillainPostControllerTest extends AcceptanceTestModule {

    @Test
    void create_a_valid_villain() throws Exception {
        String body = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"Joker",
                    "power":"está loco"
                }""";

        assertRequest("POST", "/villains", body, 201);
    }

}
