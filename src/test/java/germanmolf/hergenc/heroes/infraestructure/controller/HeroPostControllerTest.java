package germanmolf.hergenc.heroes.infraestructure.controller;

import germanmolf.hergenc.shared.infraestructure.controller.AcceptanceTestModule;
import org.junit.jupiter.api.Test;

final class HeroPostControllerTest extends AcceptanceTestModule {

    @Test
    void create_a_valid_hero() throws Exception {
        String body = """
                {
                    "id":"cc77f9b4-3209-11ee-be56-0242ac120002",
                    "name":"superheroe",
                    "power":"superpoder"
                }""";

        assertRequest("POST", "/heroes", body, 201);
    }

}
