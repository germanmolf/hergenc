package germanmolf.hergenc.heroes.infraestructure.controller;

import germanmolf.hergenc.heroes.application.create.CreateHeroRequest;
import germanmolf.hergenc.heroes.application.create.HeroCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
public final class HeroPostController {

    private final HeroCreator creator;

    public HeroPostController(HeroCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody CreateHeroRequest request) {
        creator.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
