package germanmolf.hergenc.versus.application.find;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.versus.application.VersusModuleTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class VersusSearcherTest extends VersusModuleTest {

    private VersusSearcher searcher;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        searcher = new VersusSearcher(repository);
    }

    @Test
    void search_for_versus() {
        searcher.search(new Criteria());
    }
}
