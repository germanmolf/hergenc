package com.example.heroes.versus.infraestructure.controller;

import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.shared.infraestructure.controller.ExceptionHandlerTestModule;
import com.example.heroes.versus.application.create.CreateVersusRequest;
import com.example.heroes.versus.application.create.CreateVersusRequestMother;
import com.example.heroes.versus.domain.VersusId;
import com.example.heroes.versus.domain.VersusIdMother;
import com.example.heroes.versus.domain.exceptions.*;
import com.example.heroes.villains.domain.VillainIdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class VersusExceptionHandlerTest extends ExceptionHandlerTestModule {

    private VersusPostController postController;
    private VersusGetController getController;

    @BeforeEach
    protected void setUp() {
        postController = mock(VersusPostController.class);
        getController = mock(VersusGetController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(postController, getController)
                .setControllerAdvice(new VersusExceptionHandler())
                .build();
    }

    @Test
    void versus_already_exists_response() throws Exception {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"versus_already_exists",
                    "message":"The versus <%s> already exists"
                }"""
                .formatted(request.id());
        VersusAlreadyExistsException versusAlreadyExistsException =
                new VersusAlreadyExistsException(VersusIdMother.create(request.id()));

        when(postController.post(request)).thenThrow(versusAlreadyExistsException);

        assertRequestAndResponse("POST", "/versus", body, 409, bodyResponse);
    }

    @Test
    void hero_already_defeated_response() throws Exception {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"hero_already_defeated",
                    "message":"The hero <%s> is already defeated"
                }"""
                .formatted(request.heroId());
        HeroAlreadyDefeatedException heroAlreadyDefeatedException =
                new HeroAlreadyDefeatedException(HeroIdMother.create(request.heroId()));

        when(postController.post(request)).thenThrow(heroAlreadyDefeatedException);

        assertRequestAndResponse("POST", "/versus", body, 409, bodyResponse);
    }

    @Test
    void villain_already_defeated_response() throws Exception {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"villain_already_defeated",
                    "message":"The villain <%s> is already defeated"
                }"""
                .formatted(request.villainId());
        VillainAlreadyDefeatedException villainAlreadyDefeatedException =
                new VillainAlreadyDefeatedException(VillainIdMother.create(request.villainId()));

        when(postController.post(request)).thenThrow(villainAlreadyDefeatedException);

        assertRequestAndResponse("POST", "/versus", body, 409, bodyResponse);
    }

    @Test
    void versus_defeated_null_exception_response() throws Exception {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"versus_defeated_null",
                    "message":"The versus defeated is null"
                }""";
        VersusDefeatedNullException versusDefeatedNullException = new VersusDefeatedNullException();

        when(postController.post(request)).thenThrow(versusDefeatedNullException);

        assertRequestAndResponse("POST", "/versus", body, 400, bodyResponse);
    }

    @Test
    void versus_defeated_invalid_value_exception_response() throws Exception {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"versus_defeated_invalid_value",
                    "message":"The versus defeated value <%s> is not valid"
                }"""
                .formatted(request.defeated());
        VersusDefeatedInvalidValueException versusDefeatedInvalidValueException =
                new VersusDefeatedInvalidValueException(request.defeated());

        when(postController.post(request)).thenThrow(versusDefeatedInvalidValueException);

        assertRequestAndResponse("POST", "/versus", body, 400, bodyResponse);
    }

    @Test
    void versus_not_found_exception_response() throws Exception {
        VersusId id = VersusIdMother.random();
        String body = "";
        String bodyResponse = """
                {
                    "code":"versus_not_found",
                    "message":"Not found versus <%s>"
                }"""
                .formatted(id.value());
        VersusNotFoundException versusNotFoundException = new VersusNotFoundException(id);

        when(getController.getVersus(id.value())).thenThrow(versusNotFoundException);

        assertRequestAndResponse("GET", "/versus/%s".formatted(id.value()), body, 404, bodyResponse);
    }
}
