package germanmolf.hergenc.versus.infraestructure.controller;

import germanmolf.hergenc.versus.application.create.CreateVersusRequest;
import germanmolf.hergenc.versus.application.create.VersusCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versus")
public final class VersusPostController {

    private final VersusCreator creator;

    public VersusPostController(VersusCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody CreateVersusRequest request) {
        creator.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
