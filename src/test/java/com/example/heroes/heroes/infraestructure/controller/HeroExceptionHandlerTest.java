package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.application.create.CreateHeroRequest;
import com.example.heroes.heroes.application.create.CreateHeroRequestMother;
import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.heroes.domain.exceptions.HeroAlreadyExistsException;
import com.example.heroes.shared.application.UnitTestModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class HeroExceptionHandlerTest extends UnitTestModule {

    private HeroPostController postController;
    private HeroGetController getController;
    private MockMvc mockMvc;

    @BeforeEach
    protected void setUp() {
        postController = mock(HeroPostController.class);
        getController = mock(HeroGetController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(postController, getController)
                .setControllerAdvice(new HeroExceptionHandler())
                .build();
    }

    @Test
    void hero_already_exists_response() throws Exception {
        CreateHeroRequest request = CreateHeroRequestMother.random();
        String body = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(request);
        String bodyResponse = """
                {
                    "code":"hero_already_exists",
                    "message":"The hero <%s> already exists"
                }"""
                .formatted(request.id());

        when(postController.post(request)).thenThrow(new HeroAlreadyExistsException(HeroIdMother.create(request.id())));

        assertRequestAndResponse("POST", "/heroes", body, 409, bodyResponse);
    }
//
//    @Test
//    void qwe() throws Exception {
//        HeroId id = HeroIdMother.random();
//        String body = """
//                {
//                    "id":"%s",
//                    "name":"superheroe",
//                    "power":"superpoder"
//                }"""
//                .formatted(id.value());
//        String bodyResponse = """
//                {
//                    "code":"hero_already_exists",
//                    "message":"The hero <%s> already exists"
//                }"""
//                .formatted(id.value());
//
//        when(getController.getHero(id.value())).thenThrow(new HeroAlreadyExistsException(id));
//
//        assertRequestAndResponse("GET", "/heroes/%s".formatted(id.value()), body, 409, bodyResponse);
//    }

    private void assertRequestAndResponse(String method, String url, String bodyRequest, Integer expectedStatusCode,
                                          String expectedResponse) throws Exception {
        mockMvc.perform(request(HttpMethod.valueOf(method), url).content(bodyRequest).contentType(APPLICATION_JSON))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(content().json(expectedResponse, true));
    }

}
