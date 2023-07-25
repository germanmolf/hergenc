package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.shared.infraestructure.controller.ControllerTestModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

final class HeroGetControllerTest extends ControllerTestModule {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void find_an_existing_hero() throws Exception {
        String id = "cc77f9b4-3209-11ee-be56-0242ac120002";
        String body = "{" +
                "\"id\":\"" + id + "\"," +
                "\"name\":\"superheroe\"," +
                "\"power\":\"superpoder\"" +
                "}";

        mockMvc.perform(request(HttpMethod.valueOf("POST"), "/heroes").content(body).contentType(APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(content().string(""));

        mockMvc.perform(get("/heroes/" + id))
                .andExpect(status().is(200))
                .andExpect(content().json(body));
    }
}
