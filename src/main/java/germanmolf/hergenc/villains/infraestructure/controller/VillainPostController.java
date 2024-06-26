package germanmolf.hergenc.villains.infraestructure.controller;

import germanmolf.hergenc.villains.application.create.CreateVillainRequest;
import germanmolf.hergenc.villains.application.create.VillainCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/villains")
public final class VillainPostController {

    private final VillainCreator creator;

    public VillainPostController(VillainCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody CreateVillainRequest request) {
        creator.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
