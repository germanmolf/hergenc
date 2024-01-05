package com.example.heroes.shared.infraestructure.controller;

import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Tag("AcceptanceTest")
public abstract class AcceptanceTestModule {

    @Autowired
    protected MockMvc mockMvc;

    protected void assertRequest(String method, String url, String body, Integer expectedStatusCode) throws Exception {
        mockMvc.perform(request(HttpMethod.valueOf(method), url).content(body).contentType(APPLICATION_JSON))
                .andExpect(status().is(expectedStatusCode));
    }

    protected void assertResponse(String url, Integer expectedStatusCode, String expectedResponse) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(content().json(expectedResponse, true));
    }
}
