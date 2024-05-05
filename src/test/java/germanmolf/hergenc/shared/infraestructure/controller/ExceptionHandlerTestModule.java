package germanmolf.hergenc.shared.infraestructure.controller;

import germanmolf.hergenc.shared.application.UnitTestModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExceptionHandlerTestModule extends UnitTestModule {

    protected MockMvc mockMvc;

    @BeforeEach
    protected void setUp() {
        super.setUp();
    }

    protected String objectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(object);
    }

    protected void assertRequestAndResponse(String method, String url, String bodyRequest, Integer expectedStatusCode,
                                            String expectedResponse) throws Exception {
        mockMvc.perform(request(HttpMethod.valueOf(method), url).content(bodyRequest).contentType(APPLICATION_JSON))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(content().json(expectedResponse, true));
    }

}
