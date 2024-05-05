package germanmolf.hergenc.villains.infraestructure.controller;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.infraestructure.controller.CriteriaParser;
import germanmolf.hergenc.villains.application.find.VillainFinder;
import germanmolf.hergenc.villains.application.find.VillainResponse;
import germanmolf.hergenc.villains.application.find.VillainSearcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/villains")
public final class VillainGetController {

    private final VillainFinder finder;
    private final VillainSearcher searcher;
    private final CriteriaParser criteriaParser;
    private static final HashSet<String> filterFields = new HashSet<>() {{
        add("name");
        add("power");
        add("heroesDefeatedTotal");
        add("status");
        add("heroDefeater");
    }};
    private static final HashSet<String> orderFields = new HashSet<>() {{
        add("name");
        add("power");
        add("heroesDefeatedTotal");
        add("status");
    }};

    public VillainGetController(VillainFinder finder, VillainSearcher searcher) {
        this.finder = finder;
        this.searcher = searcher;
        this.criteriaParser = new CriteriaParser(filterFields, orderFields);
    }

    @GetMapping
    public ResponseEntity<List<VillainResponse>> getVillains(@RequestParam Map<String, String> params) {
        Criteria criteria = criteriaParser.fromParams(params);
        var villains = searcher.search(criteria);
        return ResponseEntity.ok(villains);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VillainResponse> getVillain(@PathVariable String id) {
        var villain = finder.find(id);
        return ResponseEntity.ok(villain);
    }
}
