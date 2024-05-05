package germanmolf.hergenc.villains.application.find;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.villains.VillainModuleTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class VillainSearcherTest extends VillainModuleTest {

    private VillainSearcher searcher;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        searcher = new VillainSearcher(repository);
    }

    @Test
    void search_for_villains() {
        searcher.search(new Criteria());
    }
}
