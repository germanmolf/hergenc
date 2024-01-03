package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.application.create.CreateHeroRequest;
import com.example.heroes.heroes.application.create.CreateHeroRequestMother;
import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.heroes.domain.exceptions.*;
import com.example.heroes.shared.infraestructure.controller.ExceptionHandlerTestModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class HeroExceptionHandlerTest extends ExceptionHandlerTestModule {

    private HeroPostController postController;
    private HeroGetController getController;

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
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"hero_already_exists",
                    "message":"The hero <%s> already exists"
                }"""
                .formatted(request.id());
        HeroAlreadyExistsException heroAlreadyExistsException = new HeroAlreadyExistsException(HeroIdMother.create(request.id()));

        when(postController.post(request)).thenThrow(heroAlreadyExistsException);

        assertRequestAndResponse("POST", "/heroes", body, 409, bodyResponse);
    }

    @Test
    void hero_name_null_response() throws Exception {
        CreateHeroRequest request = CreateHeroRequestMother.withNameNull();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"hero_name_null",
                    "message":"The hero name is null"
                }""";
        HeroNameNullException heroNameNullException = new HeroNameNullException();

        when(postController.post(request)).thenThrow(heroNameNullException);

        assertRequestAndResponse("POST", "/heroes", body, 400, bodyResponse);
    }

    @Test
    void hero_name_invalid_length_response() throws Exception {
        CreateHeroRequest request = CreateHeroRequestMother.withNameOverLength();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"hero_name_invalid_length",
                    "message":"The hero name length must be between 1 and 20 letters"
                }""";
        HeroNameInvalidLengthException heroNameInvalidLengthException = new HeroNameInvalidLengthException();

        when(postController.post(request)).thenThrow(heroNameInvalidLengthException);

        assertRequestAndResponse("POST", "/heroes", body, 400, bodyResponse);
    }

    @Test
    void hero_power_null_response() throws Exception {
        CreateHeroRequest request = CreateHeroRequestMother.withPowerNull();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"hero_power_null",
                    "message":"The hero power is null"
                }""";
        HeroPowerNullException heroPowerNullException = new HeroPowerNullException();

        when(postController.post(request)).thenThrow(heroPowerNullException);

        assertRequestAndResponse("POST", "/heroes", body, 400, bodyResponse);
    }

    @Test
    void hero_power_invalid_length_response() throws Exception {
        CreateHeroRequest request = CreateHeroRequestMother.withPowerOverLength();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"hero_power_invalid_length",
                    "message":"The hero power length must be between 1 and 30 letters"
                }""";
        HeroPowerInvalidLengthException heroPowerInvalidLengthException = new HeroPowerInvalidLengthException();

        when(postController.post(request)).thenThrow(heroPowerInvalidLengthException);

        assertRequestAndResponse("POST", "/heroes", body, 400, bodyResponse);
    }

    @Test
    void hero_not_found_response() throws Exception {
        HeroId id = HeroIdMother.random();
        String body = "";
        String bodyResponse = """
                {
                    "code":"hero_not_found",
                    "message":"Not found hero <%s>"
                }"""
                .formatted(id.value());
        HeroNotFoundException heroNotFoundException = new HeroNotFoundException(id);

        when(getController.getHero(id.value())).thenThrow(heroNotFoundException);

        assertRequestAndResponse("GET", "/heroes/%s".formatted(id.value()), body, 404, bodyResponse);
    }

}
