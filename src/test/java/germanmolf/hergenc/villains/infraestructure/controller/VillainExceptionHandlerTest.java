package germanmolf.hergenc.villains.infraestructure.controller;

import germanmolf.hergenc.shared.infraestructure.controller.ExceptionHandlerTestModule;
import germanmolf.hergenc.villains.application.create.CreateVillainRequestMother;
import germanmolf.hergenc.villains.domain.VillainIdMother;
import germanmolf.hergenc.villains.application.create.CreateVillainRequest;
import germanmolf.hergenc.villains.domain.VillainId;
import germanmolf.hergenc.villains.domain.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class VillainExceptionHandlerTest extends ExceptionHandlerTestModule {

    private VillainPostController postController;
    private VillainGetController getController;

    @BeforeEach
    protected void setUp() {
        postController = mock(VillainPostController.class);
        getController = mock(VillainGetController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(postController, getController)
                .setControllerAdvice(new VillainExceptionHandler())
                .build();
    }

    @Test
    void villain_already_exists_response() throws Exception {
        CreateVillainRequest request = CreateVillainRequestMother.random();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"villain_already_exists",
                    "message":"The villain <%s> already exists"
                }"""
                .formatted(request.id());
        VillainAlreadyExistsException villainAlreadyExistsException =
                new VillainAlreadyExistsException(VillainIdMother.create(request.id()));

        when(postController.post(request)).thenThrow(villainAlreadyExistsException);

        assertRequestAndResponse("POST", "/villains", body, 409, bodyResponse);
    }

    @Test
    void villain_name_null_response() throws Exception {
        CreateVillainRequest request = CreateVillainRequestMother.withNameNull();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"villain_name_null",
                    "message":"The villain name is null"
                }""";
        VillainNameNullException villainNameNullException = new VillainNameNullException();

        when(postController.post(request)).thenThrow(villainNameNullException);

        assertRequestAndResponse("POST", "/villains", body, 400, bodyResponse);
    }

    @Test
    void villain_name_invalid_length_response() throws Exception {
        CreateVillainRequest request = CreateVillainRequestMother.withNameOverLength();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"villain_name_invalid_length",
                    "message":"The villain name length must be between 1 and 20 letters"
                }""";
        VillainNameInvalidLengthException villainNameInvalidLengthException = new VillainNameInvalidLengthException();

        when(postController.post(request)).thenThrow(villainNameInvalidLengthException);

        assertRequestAndResponse("POST", "/villains", body, 400, bodyResponse);
    }

    @Test
    void villain_power_null_response() throws Exception {
        CreateVillainRequest request = CreateVillainRequestMother.withPowerNull();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"villain_power_null",
                    "message":"The villain power is null"
                }""";
        VillainPowerNullException villainPowerNullException = new VillainPowerNullException();

        when(postController.post(request)).thenThrow(villainPowerNullException);

        assertRequestAndResponse("POST", "/villains", body, 400, bodyResponse);
    }

    @Test
    void villain_power_invalid_length_response() throws Exception {
        CreateVillainRequest request = CreateVillainRequestMother.withPowerOverLength();
        String body = objectToJson(request);
        String bodyResponse = """
                {
                    "code":"villain_power_invalid_length",
                    "message":"The villain power length must be between 1 and 30 letters"
                }""";
        VillainPowerInvalidLengthException villainPowerInvalidLengthException =
                new VillainPowerInvalidLengthException();

        when(postController.post(request)).thenThrow(villainPowerInvalidLengthException);

        assertRequestAndResponse("POST", "/villains", body, 400, bodyResponse);
    }

    @Test
    void villain_not_found_response() throws Exception {
        VillainId id = VillainIdMother.random();
        String body = "";
        String bodyResponse = """
                {
                    "code":"villain_not_found",
                    "message":"Not found villain <%s>"
                }"""
                .formatted(id.value());
        VillainNotFoundException villainNotFoundException = new VillainNotFoundException(id);

        when(getController.getVillain(id.value())).thenThrow(villainNotFoundException);

        assertRequestAndResponse("GET", "/villains/%s".formatted(id.value()), body, 404, bodyResponse);
    }

}
